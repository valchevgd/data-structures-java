public class InsertionSortReqursive {

    public static void main(String[] args) {

        int[] intArray = {20, 35, -15, 7, 55, 1, -22};

        insertionSort(intArray, intArray.length);

        for (int i : intArray) {
            System.out.println(i);
        }
    }

    public static void insertionSort(int[] input, int numItems) {

        if (numItems < 2) {
            return;
        }
        int lastElement = numItems - 1;

        insertionSort(input, lastElement);

        int newElement = input[lastElement];

        int i;

        for (i = lastElement; i > 0 && input[i - 1] > newElement; i--) {
            input[i] = input[i - 1];
        }

        input[i] = newElement;
    }
}
