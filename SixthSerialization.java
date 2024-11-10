import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Define a User class that implements Serializable
class User implements Serializable {
    private static final long serialVersionUID = 1L; // For version control
    private int id;
    private String name;
    private String email;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}

public class SixthSerialization {

    // Method to serialize the list of users to a file
    public static void serializeUsers(List<User> users, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(users);
            System.out.println("Serialization successful. Users written to " + filename);
        } catch (IOException e) {
            System.err.println("Serialization error: " + e.getMessage());
        }
    }

    // Method to deserialize users from a file
    @SuppressWarnings("unchecked")
    public static List<User> deserializeUsers(String filename) {
        List<User> users = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            users = (List<User>) ois.readObject();
            System.out.println("Deserialization successful. Users read from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Deserialization error: " + e.getMessage());
        }
        return users;
    }

    public static void main(String[] args) {
        String filename = "users.ser"; // File name for serialized data

        // Create a list of users
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Brahmjot", "Brahmjot@example.com"));
        users.add(new User(2, "Gurjot", "Gurjot@example.com"));
        users.add(new User(3, "Aman", "Aman@example.com"));

        // Serialize the users to a file
        serializeUsers(users, filename);

        // Deserialize the users from the file
        List<User> deserializedUsers = deserializeUsers(filename);

        // Print the deserialized users
        if (deserializedUsers != null) {
            System.out.println("\nDeserialized Users:");
            for (User user : deserializedUsers) {
                System.out.println(user);
            }
        }
        System.out.println("Brahmjot Singh AI-ML 03913211621");
    }
}
