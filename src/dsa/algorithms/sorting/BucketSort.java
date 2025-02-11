package dsa.algorithms.sorting;

import java.util.List;
import java.util.ArrayList;

public class BucketSort {
    public static void main(String[] args) {
        float[] array = { 0.9f, 0.8f, 0.7f, 0.6f, 0.5f, 0.4f, 0.2f, 0.3f, 0.1f, 0.98f };

        bucketSort(array);

        for (float num : array) {
            System.out.println(num);
        }
    }

    public static void bucketSort(final float[] array) {
        final List<Float>[] buckets = new ArrayList[array.length];

        // Creating Buckets
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Put array elements in corresponding buckets
        for (int i = 0; i < array.length; i++) {
            int bucketId = (int) (array[i] * array.length);
            buckets[bucketId].add(array[i]);
        }

        for (int i = 0; i < buckets.length; i++) {
            if (!buckets[i].isEmpty()) {
                insertionSort(buckets[i]);
            }
        }

        int index = 0;
        for (int i = 0; i < buckets.length; i++) {
            if(!buckets[i].isEmpty()) {
                for (int j = 0; j < buckets[i].size(); j++) {
                    array[index] = buckets[i].get(j);
                    index++;
                }
            }
        }

    }

    private static void insertionSort(final List<Float> list) {
        for (int i = 1; i < list.size(); i++) {
            float temp = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j) > temp) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, temp);
        }
    }
}