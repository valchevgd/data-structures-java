import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {

    public static void main(String[] args) {
        int[] intArray = { 54, 46, 83, 66, 95, 92, 43 };

        bucketSort(intArray);

        for (int num : intArray) {
            System.out.println(num);
        }
    }

    private static void bucketSort(int[] input) {
        List<Integer>[] buckets = new List[10];

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Integer>();
        }

        for (int value : input) {
            buckets[hash(value)].add(value);
        }

        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        int j = 0;

        for (List<Integer> bucket : buckets) {
            for (Integer integer : bucket) {
                input[j++] = integer;
            }
        }
    }

    private static int hash(int value) {
        return value / (int) 10 % 10;
    }
}
