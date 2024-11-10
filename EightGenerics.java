import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class EightGenerics {

    // Generic method for sorting a list
    public static <T extends Comparable<T>> void sortList(List<T> list) {
        Collections.sort(list);
    }

    // Generic method for searching an item in a list
    public static <T> int searchItem(List<T> list, T item) {
        return list.indexOf(item);
    }

    // Generic method for filtering a list based on a predicate
    public static <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        List<T> filteredList = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    public static void main(String[] args) {
        // Create a list of integers
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(1);
        numbers.add(3);

        // Sort the list of integers
        System.out.println("Original list: " + numbers);
        sortList(numbers);
        System.out.println("Sorted list: " + numbers);

        // Search for an item in the list of integers
        int searchItem = 1;
        int index = searchItem(numbers, searchItem);
        System.out.println("Item " + searchItem + " found at index: " + index);

        // Create a list of names (Strings)
        List<String> names = new ArrayList<>();
        names.add("Shubh");
        names.add("Arshpreet");
        names.add("Sahib");
        names.add("Prabhjot");
        names.add("Brahmjot");

        // Sort the list of names
        System.out.println("\nOriginal names list: " + names);
        sortList(names);
        System.out.println("Sorted names list: " + names);

        // Filter names that start with 'S'
        List<String> filteredNames = filterList(names, name -> name.startsWith("B"));
        System.out.println("Names starting with 'B': " + filteredNames);
        System.out.println("Brahmjot Singh AI-ML 03913211621");
    }
}
