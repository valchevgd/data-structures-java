public class Heap {

    private int[] heap;
    private int size;

    public Heap(int capacity) {
        this.heap = new int[capacity];
    }

    public void insert(int value) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("Heap is full");
        }

        heap[size] = value;

        heapifyUp(size);
        size++;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }

        return heap[0];
    }

    public int delete(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }

        int parent = getParent(index);
        int deletedValue = heap[index];

        heap[index] = heap[size - 1];

        if (index == 0 || heap[index] < heap[parent]) {
            heapifyDown(index, size - 1);
        } else {
            heapifyUp(index);
        }

        size--;
        return deletedValue;
    }

    public void sort() {
        int lastHeapIndex = size - 1;
        for (int i = 0; i < lastHeapIndex; i++) {
            int temp = heap[0];
            heap[0] = heap[lastHeapIndex - i];
            heap[lastHeapIndex - i] = temp;

            heapifyDown(0, lastHeapIndex - i - 1);
        }
    }

    private void heapifyUp(int index) {
        int newValue = heap[index];

        while (index > 0 && newValue > heap[getParent(index)]) {
            heap[index] = heap[getParent(index)];
            index = getParent(index);
        }

        heap[index] = newValue;
    }

    private void heapifyDown(int index, int lastHeapIndex) {
        int indexOfChildToSwap;

        while (index <= lastHeapIndex) {
            int leftChildIndex = getChild(index, true);
            int rightChildIndex = getChild(index, false);

            if (leftChildIndex <= lastHeapIndex) {
                if (rightChildIndex > lastHeapIndex) {
                    indexOfChildToSwap = leftChildIndex;
                } else {
                    indexOfChildToSwap = (heap[leftChildIndex] > heap[rightChildIndex] ? leftChildIndex : rightChildIndex);
                }

                if (heap[index] < heap[indexOfChildToSwap]) {
                    int temp = heap[index];
                    heap[index] = heap[indexOfChildToSwap];
                    heap[indexOfChildToSwap] = temp;
                } else {
                    break;
                }

                index = indexOfChildToSwap;
            } else {
                break;
            }
        }
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + ", ");
        }
        System.out.println();
    }

    public boolean isFull() {
        return size == heap.length;
    }

    public int getParent(int index) {
        return (index - 1) / 2;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getChild(int index, boolean left) {
        return 2 * index + (left ? 1 : 2);
    }
}
