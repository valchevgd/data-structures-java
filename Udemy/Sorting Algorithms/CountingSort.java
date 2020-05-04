public class CountingSort {

    public static void main(String[] args) {

        int[] intArray = { 2, 5, 9, 7, 6, 2, 4, 1, 10, 8 };

        countingSort(intArray, 1, 10);

        for (int num : intArray) {
            System.out.println(num);
        }
    }

    public static void countingSort(int[] input, int min, int max) {

        int[] countArray = new int[(max - min) + 1];

        for (int i = 0; i < input.length; i++) {

            countArray[input[i] - min]++;
        }

        int j = 0;

        for (int i = min; i <= max; i++) {
            while (countArray[i - min] > 0) {
                input[j++] = i;
                countArray[i - min]--;
            }
        }
    }
}
