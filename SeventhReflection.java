import java.lang.reflect.Field;
import java.util.Scanner;

// Sample class with private fields
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

public class SeventhReflection {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Create an instance of Person
        Person person = new Person("Brahmjot Singh", 20);
        System.out.println("Original Person: " + person.getName() + ", Age: " + person.getAge());

        // Get the class of the Person object
        Class<?> personClass = person.getClass();

        // Modify the fields based on user input
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new age: ");
        int newAge = scanner.nextInt();

        try {
            // Access the private fields using reflection
            Field nameField = personClass.getDeclaredField("name");
            Field ageField = personClass.getDeclaredField("age");

            // Make the fields accessible
            nameField.setAccessible(true);
            ageField.setAccessible(true);

            // Modify the values of the fields
            nameField.set(person, newName);
            ageField.set(person, newAge);

            // Print the modified Person object
            System.out.println("Modified Person: " + person.getName() + ", Age: " + person.getAge());

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        scanner.close();
        System.out.println("Brahmjot Singh AI-ML 03913211621");
    }
}
