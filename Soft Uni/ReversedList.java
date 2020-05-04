package implementations;

import interfaces.List;

import java.util.Iterator;

public class ReversedList<E> implements List<E> {

    private final int DEFAULT_CAPACITY = 2;
    private int size;
    private Object[] elements;

    public ReversedList() {
        this.size = 0;
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(Object element) {
        this.ensureSize();
        this.shiftRight();
        this.elements[0] = element;
        this.size++;
    }

    @Override
    public void removeAt(int index) {

        this.checkIndex(index);

        E value = (E) this.elements[index];

        this.shiftLeft(index);

        this.size--;
    }

    @Override
    public E get(int index) {
        this.checkIndex(index);

        return (E)this.elements[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.elements.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < size;
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }

    private void ensureSize() {
        if (this.size == this.elements.length) {
            this.grow();
        }
    }

    private void grow() {
        Object[] temp = new Object[this.elements.length * 2];

        System.arraycopy(this.elements, 0, temp, 0, this.elements.length);

        this.elements = temp;
    }

    private void checkIndex(int index) {

        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void shiftRight() {

        for (int i = this.size - 1; i >= 0; i--) {
            this.elements[i + 1] = this.elements[i];
        }
    }

    private void shiftLeft(int index) {

        for (int i = index; i < this.size; i++) {
            this.elements[i] = this.elements[i + 1];
        }
    }
}
