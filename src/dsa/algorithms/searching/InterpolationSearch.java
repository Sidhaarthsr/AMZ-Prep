package dsa.algorithms.searching;

/**
 * Best used for uniformly distributed data
 * 
 * Average case : 0(log(log(n)))
 * Worst case : 0(n) [values increase exponentially]
 */
public class InterpolationSearch {
    public static void main(String[] args) {
        int size = 10000000;
        final int[] array = new int[size];
        // for (int i = 0; i < array.length; i++) {
        //     if(i%3==0) {
        //         array[i] = i + 1;
        //     }
        // }
        final int position = interpolationSearch(array, 40);
        if (position == -1) {
            System.out.println("Element not found");
        } else {
            System.out.println("Element found at : " + position);
        }

    }

    private static int interpolationSearch(final int[] array, final int target) {
        int high = array.length - 1;
        int low = 0;

        while (target >= array[low] && target <= array[high] && low <= high) {
            int probe = low + (high - low) * (target - array[low]) / (array[high] - array[low]);

            System.out.println("Probe : " + probe);

            if (array[probe] == target) {
                return probe;
            } else if (array[probe] < target) {
                low = probe + 1;
            } else {
                high = probe - 1;
            }
        }
        return -1;
    }
}
