import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ElevenFunctions {

    public static void main(String[] args) {
        // Sample list of integers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9,10);

        // 1. Filtering even numbers
        List<Integer> evenNumbers = numbers.stream()
                .filter(number -> number % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Even Numbers: " + evenNumbers);

        // 2. Mapping to squares
        List<Integer> squares = numbers.stream()
                .map(number -> number * number)
                .collect(Collectors.toList());

        System.out.println("Squares: " + squares);

        // 3. Filtering and Mapping (even numbers to their squares)
        List<Integer> evenSquares = numbers.stream()
                .filter(number -> number % 2 == 0)
                .map(number -> number * number)
                .collect(Collectors.toList());

        System.out.println("Even Squares: " + evenSquares);

        // 4. Reducing to a single value (sum of even numbers)
        int evenSum = numbers.stream()
                .filter(number -> number % 2 == 0)
                .reduce(0, Integer::sum);

        System.out.println("Sum of Even Numbers: " + evenSum);

        // 5. Reducing to a single value (sum of even squares)
        int evenSquaresSum = evenSquares.stream()
                .reduce(0, Integer::sum);

        System.out.println("Sum of Even Squares: " + evenSquaresSum);
        System.out.println("Brahmjot Singh AI-ML 03913211621");
    }
}
