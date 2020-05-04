public class MergeSortDesc {

    public static void main(String[] args) {

        int[] intArray = {20, 35, -15, 7, 55, 1, -22};

        mergeSort(intArray, 0, intArray.length);

        for (int num : intArray) {
            System.out.println(num);
        }
    }

    public static void mergeSort(int[] input, int startIndex, int endIndex) {

        if (endIndex - startIndex < 2) {
            return;
        }


        int mid = (startIndex + endIndex) / 2;

        mergeSort(input, startIndex, mid);

        mergeSort(input, mid, endIndex);

        merge(input, startIndex, mid, endIndex);
    }

    private static void merge(int[] input, int startIndex, int mid, int endIndex) {

        if (input[mid - 1] >= input[mid]) {
            return;
        }

        int i = startIndex;

        int j = mid;

        int tempIndex = 0;

        int[] temp = new int[endIndex - startIndex];

        while (i < mid && j < endIndex) {
            temp[tempIndex++] = input[i] >= input[j] ? input[i++] : input[j++];
        }

        System.arraycopy(input, i, input, startIndex + tempIndex, mid - i);

        System.arraycopy(temp, 0, input, startIndex, tempIndex);
    }
}
