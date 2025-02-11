package dsa.algorithms.sorting;

/**
 * Bubble sort orders the array by swapping numbers with their adjacent elements one by one if they're out of place
 * Time Complexity : Quadratic O(n^2)
 * Small datasets - maybe for simplicity and in-place sorting
 * Large datasets - never
 */
public class BubbleSort {
    public static void main(String[] args) {

        int[] array = { 9, 8, 7, 6, 5, 4, 2, 3, 1 };

        bubbleSort(array);

        for (int num : array) {
            System.out.print(num);
        }

    }

    private static void bubbleSort(final int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    //Swapping values
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }

        }
    }

}
