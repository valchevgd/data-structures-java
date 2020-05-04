package implementations;

import interfaces.List;

import java.util.Iterator;

public class ArrayList<E> implements List<E> {

    private Object[] elements;
    private int size;

    public ArrayList(){
        this.elements = new Object[4];
        this.size = 0;
    }

    @Override
    public boolean add(E element) {

        this.ensureSize();

        this.elements[this.size++] = element;

        return true;
    }

    @Override
    public boolean add(int index, E element) {

        this.checkIndex(index);

        this.ensureSize();

        this.shiftRight(index);

        this.elements[index] = element;

        this.size++;

        return true;
    }

    @Override
    public E get(int index) {
        this.checkIndex(index);

        return (E)this.elements[index];
    }

    @Override
    public E set(int index, E element) {

        this.checkIndex(index);

        this.ensureSize();

        E value = (E)this.elements[index];

        this.shiftRight(index);

        this.elements[index] = element;

        return value;
    }

    @Override
    public E remove(int index) {

        this.checkIndex(index);

        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }

        E value = (E) this.elements[index];

        this.shiftLeft(index);

        this.size--;

        return value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {

        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(E element) {

        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(element)) {
                return true ;
            }
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
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

    private void shiftRight(int index) {

        for (int i = this.size - 1; i >= index; i--) {
            this.elements[i + 1] = this.elements[i];
        }
    }

    private void shiftLeft(int index) {

        for (int i = index; i < this.size; i++) {
            this.elements[i] = this.elements[i + 1];
        }
    }
}
