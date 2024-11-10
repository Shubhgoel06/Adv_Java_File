import java.sql.*;

public class FourthDatabaseOperations {
    private static final String URL = "jdbc:mysql://localhost:3306/employee1"; // Database URL
    private static final String USER = "root"; // MySQL username
    private static final String PASSWORD = "123456"; // MySQL password
    
    private Connection connection;

    // Constructor to establish the database connection
    public FourthDatabaseOperations() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (connection != null) {
                System.out.println("Database connected successfully.");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.out.println("Error while connecting to database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Insert a new record into the users table
    public void insertRecord(String name, String email, int age) {
        String query = "INSERT INTO employee (name, email, age) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setInt(3, age);
            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted + " row(s) inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all records from the users table
    public void retrieveRecords() {
        String query = "SELECT * FROM employee";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("ID | Name | Email | Age");
            while (resultSet.next()) {
                System.out.printf("%d | %s | %s | %d%n",
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update a user’s details based on their ID
    public void updateRecord(int id, String name, String email, int age) {
        String query = "UPDATE employee SET name = ?, email = ?, age = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setInt(3, age);
            statement.setInt(4, id);
            int rowsUpdated = statement.executeUpdate();
            System.out.println(rowsUpdated + " row(s) updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a record based on the user ID
    public void deleteRecord(int id) {
        String query = "DELETE FROM employee WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            System.out.println(rowsDeleted + " row(s) deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Use a transaction to insert multiple records atomically
    public void insertMultipleRecordsWithTransaction() {
        String query1 = "INSERT INTO employee (name, email, age) VALUES ('Gurpreet singh', 'Gur@example.com', 20)";
        String query2 = "INSERT INTO employee (name, email, age) VALUES ('Namanjot', 'namanjot@example.com', 21)";
        
        try {
            connection.setAutoCommit(false);  // Begin transaction
            
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(query1);
                statement.executeUpdate(query2);
                
                connection.commit();  // Commit transaction if successful
                System.out.println("Transaction committed successfully.");
            } catch (SQLException e) {
                connection.rollback();  // Rollback transaction if any error occurs
                System.out.println("Transaction rolled back due to error.");
                e.printStackTrace();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);  // Restore default commit behavior
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Main method to test the operations
    public static void main(String[] args) {
        // Initialize the database operations object
        FourthDatabaseOperations dbOps = new FourthDatabaseOperations();
        
        // Example usage
        dbOps.insertRecord("Brahmjot Singh", "brahmjot@example.com", 21);
        dbOps.retrieveRecords();
        dbOps.updateRecord(1, "Arshpreet Singh", "arsh@example.com", 22);
        dbOps.deleteRecord(1);
        dbOps.insertMultipleRecordsWithTransaction();
    }
}
