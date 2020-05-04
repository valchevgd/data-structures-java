public class QuickSort {

    public static void main(String[] args) {

        int[] intArray = { 20, 35, -15, 7, 55, 1, -22 };

        quickSort(intArray, 0, intArray.length);

        for (int num : intArray) {
            System.out.println(num);
        }
    }

    public static void quickSort(int[] input, int startIndex, int endIndex) {

        if (endIndex - startIndex < 2) {
            return;
        }


        int pivotIndex = partition(input, startIndex, endIndex);

        quickSort(input, startIndex, pivotIndex);

        quickSort(input, pivotIndex + 1, endIndex);
    }

    private static int partition(int[] input, int startIndex, int endIndex) {

        int pivot = input[startIndex];

        int i = startIndex;
        int j = endIndex;

        while (i < j) {

            while (i < j && input[--j] >= pivot);

            if (i < j) {
                input[i] = input[j];
            }

            while (i < j && input[++i] <= pivot);
            if (i < j) {

                input[j] = input[i];
            }
        }


        input[j] = pivot;

        return j;
    }
}
