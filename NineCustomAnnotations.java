import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

// Define a custom annotation
@Retention(RetentionPolicy.RUNTIME)
@interface MyCustomAnnotation {
    String value();
}

// Class with methods that use the custom annotation
class Demo {

    @MyCustomAnnotation("This method adds two numbers.")
    public int add(int a, int b) {
        return a + b;
    }

    @MyCustomAnnotation("This method subtracts two numbers.")
    public int subtract(int a, int b) {
        return a - b;
    }
}

public class NineCustomAnnotations {
    public static void main(String[] args) {
        Demo demo = new Demo();

        // Reflectively inspect methods for annotations
        Method[] methods = demo.getClass().getDeclaredMethods();
        for (Method method : methods) {
            // Check if the method has the custom annotation
            if (method.isAnnotationPresent(MyCustomAnnotation.class)) {
                MyCustomAnnotation annotation = method.getAnnotation(MyCustomAnnotation.class);
                System.out.println("Method: " + method.getName() + " - " + annotation.value());

                // Call the method for demonstration
                try {
                    if (method.getName().equals("add")) {
                        System.out.println("Result of add: " + method.invoke(demo, 8, 5));
                    } else if (method.getName().equals("subtract")) {
                        System.out.println("Result of subtract: " + method.invoke(demo, 8, 5));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Brahmjot Singh AI-ML 03913211621");
    }
}
