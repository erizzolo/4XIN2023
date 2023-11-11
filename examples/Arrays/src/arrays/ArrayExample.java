package arrays;

import java.util.Arrays;

/**
 *
 * @author emanuele
 */
public class ArrayExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // example of util.Arrays usages
        System.out.println("Examples of java.util.Arrays");
        int[] original = {2, 3, 5, 7, 11, 13};
        System.out.println(Arrays.toString(original));
        int[] copyOf = Arrays.copyOf(original, 10);
        System.out.println(Arrays.toString(copyOf));
        int search = 5;
        int position = Arrays.binarySearch(original, search);
        System.out.println(search + " found at index " + position);
        int[] copyOfRange = Arrays.copyOfRange(original, 2, 7);
        System.out.println(Arrays.toString(copyOfRange));
        Arrays.parallelPrefix(copyOfRange, 0, copyOfRange.length, (left, right) -> {
            return left - right;
        });
        System.out.println(Arrays.toString(copyOfRange));
        System.out.println("Sorting...");
        Arrays.parallelSort(copyOfRange);
        System.out.println(Arrays.toString(copyOfRange));
        System.out.println("Ignore the rest ...");
        int[] newArray = Arrays.stream(copyOfRange).filter((value) -> {
            return value > -10;
        }).toArray();
        System.out.println(Arrays.toString(newArray));
    }

}
