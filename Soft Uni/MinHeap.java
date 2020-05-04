package solutions;

import interfaces.Decrease;
import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinHeap<E extends Comparable<E> & Decrease<E>> implements Heap<E> {
    private List<E> data;

    public MinHeap() {
        data = new ArrayList<>();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void add(E element) {
        this.data.add(element);
        this.heapifyUp();
    }

    private void heapifyUp() {
        int index = this.size() - 1;
        int parentIndex = this.getParentIndexFor(index);

        while (index > 0 && isLess(index, parentIndex)) {
            Collections.swap(this.data, index, parentIndex);
            index = parentIndex;
            parentIndex = this.getParentIndexFor(index);
        }
    }

    private boolean isLess(int first, int second) {
        return this.data.get(first).compareTo(this.data.get(second)) < 0;
    }

    private int getParentIndexFor(int index) {
        return (index - 1) / 2;
    }

    @Override
    public E peek() {
        ensureNonEmpty();

        return data.get(0);
    }

    private void ensureNonEmpty() {
        if (this.data.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    public E poll() {
        ensureNonEmpty();

        Collections.swap(this.data, 0, this.data.size() - 1);
        E toRemove = this.data.remove(this.data.size() - 1);

        this.heapifyDown();

        return toRemove;
    }

    private void heapifyDown() {
        int index = 0;
        int swapIndex = this.getLeftChildIndex(index);

        while (swapIndex < this.data.size()) {
            int rightChildIndex = this.getRightChildIndex(index);

            if (rightChildIndex < this.data.size()) {
                swapIndex = isLess(swapIndex, rightChildIndex) ? swapIndex : rightChildIndex;
            }

            if (isLess(index, swapIndex)) {
                break;
            }

            Collections.swap(this.data, index, swapIndex);
            index = swapIndex;
            swapIndex = getLeftChildIndex(index);
        }
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    @Override
    public void decrease(E element) {
        this.data.remove(element);
        element.decrease();
        this.add(element);
    }
}
