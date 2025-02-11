package dsa.algorithms.searching;

/**
 * Best for smaller & unsorted arrays
 * Brute force search for the target
 * Time complexity : O(n)
 */
public class LinearSearch {

    public static void main(String[] args) {
        int size = 1000000000;
        final int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = i+1;
        }
        int position = linearSearch(array, 0);
        if (position == -1) {
            System.out.println("Element not found");
        } else {
            System.out.println("Element found at : " + position);
        }

        position = linearSearch(array, 2000000000);
        if (position == -1) {
            System.out.println("Element not found");
        } else {
            System.out.println("Element found at : " + position);
        }
    }

    private static int linearSearch(final int[] array, final int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
