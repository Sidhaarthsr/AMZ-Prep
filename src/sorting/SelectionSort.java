package sorting;

/**
 * In place comparison that keeps track of minimum value during each iteration
 * 
 * The minimum element is moved to the start of the unsorted subsection after
 * each iteration
 * 
 * Time Complexity : Quadratic O(n^2)
 * Small datasets - maybe for in-place sorting
 * Large datasets - never
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] array = { 9, 8, 7, 6, 5, 4, 2, 3, 1 };

        selectionSort(array);

        for (int num : array) {
            System.out.print(num);
        }
    }

    private static void selectionSort(final int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[min] > array[j]) {
                    min = j;
                }
            }
            int tmp = array[i];
            array[i] = array[min];
            array[min] = tmp;
        }
    }
}
