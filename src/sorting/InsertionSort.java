package sorting;

/**
 * Compare elements to the left until the reference element is larger than a value to the left
 * Time Complexity : Quadratic O(n^2)
 * Small datasets - maybe for simplicity and in-place sorting
 * Large datasets - never
 * 
 * Less steps than Bubble Sort
 * Better Best Case of O(n) compared to Selection Sort O(n^2) 
 * 
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] array = { 9, 8, 7, 6, 5, 4, 2, 3, 1 };

        insertionSort(array);

        for (int num : array) {
            System.out.print(num);
        }
    }

    private static void insertionSort(final int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > temp) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = temp;
        }
    }

}
