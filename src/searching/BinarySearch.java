package searching;

/**
 * Best used for large and sorted arrays
 * 
 * Time complexity : O(log(n))
 */
public class BinarySearch {

    public static void main(String[] args) {
        int size = 1000000000;
        final int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = i+1;
        }
        final int position = binarySearch(array, 40);
        if (position == -1) {
            System.out.println("Element not found");
        } else {
            System.out.println("Element found at : " + position);
        }
    }

    private static int binarySearch(final int[] array, final int target) {
        int low = 0;
        int high = array.length-1;

        while(low<=high) {
            int mid = low + (high-low)/2;
            int value = array[mid];

            System.out.println(mid +" "+value);

            if(value<target) {
                low = mid+1;
            }else if(value>target) {
                high = mid-1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
