package implementations;

import interfaces.Deque;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {

    private final int DEFAULT_CAPACITY = 7;
    private int head;
    private int tail;
    private int size;
    private Object[] elements;

    public ArrayDeque() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.head = DEFAULT_CAPACITY / 2;
        this.tail = this.head;
        this.size = 0;
    }

    @Override
    public void add(E element) {
        ensureCapacity();

        this.elements[this.tail++] = element;
        this.size++;
    }

    @Override
    public void offer(E element) {
        add(element);
    }

    @Override
    public void addFirst(E element) {
        ensureCapacity();

        this.elements[--this.head] = element;
        this.size++;
    }

    @Override
    public void addLast(E element) {
        add(element);
    }

    @Override
    public void push(E element) {
        this.addLast(element);
    }

    @Override
    public void insert(int index, E element) {
        ensureCapacity();

        int realIndex = this.checkIndex(index);

        shiftRight(realIndex);

        this.elements[realIndex] = element;

        this.tail++;
        this.size++;
    }

    @Override
    public void set(int index, E element) {
        int realIndex = this.checkIndex(index);

        this.elements[realIndex] = element;
    }

    @Override
    public E peek() {

        if (isEmpty()) {
            return null;
        } else {
            return this.get(this.tail - 1 - this.head);
        }
    }

    @Override
    public E poll() {

        if (isEmpty()) {
            return null;
        }

        return this.remove(0);

    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }

        return this.remove(this.size - 1);
    }

    @Override
    public E get(int index) {

        int realIndex = checkIndex(index);

        return (E)this.elements[realIndex];
    }

    @Override
    public E get(Object object) {

        for (int i = this.head; i < this.tail; i++) {
            if (elements[i].equals(object)) {
                return (E)elements[i];
            }
        }

        return null;
    }

    @Override
    public E remove(int index) {

        E element = get(index);

        shiftLeft(index);

        this.size--;
        this.tail--;

        return element;
    }

    @Override
    public E remove(Object object) {

        for (int i = this.head; i < this.tail; i++) {
            if (elements[i].equals(object)) {
                return this.remove(i - this.head);
            }
        }

        return null;
    }

    @Override
    public E removeFirst() {
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(this.size - 1);
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
    public void trimToSize() {
        Object[] newElements = new Object[this.size];
        int index = this.head;

        for (int i = 0; i < newElements.length; i++) {
            newElements[i] = this.elements[index++];
        }

        this.head = 0;
        this.tail = newElements.length - 1;

        this.elements = newElements;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int startIndex = head;
            @Override
            public boolean hasNext() {
                return startIndex < tail;
            }

            @Override
            public E next() {
                return (E)elements[startIndex++];
            }
        };
    }

    private void ensureCapacity() {

        if (!(this.head > 0) || !(this.tail < this.elements.length)) {
            grow();
        }

    }

    private void grow() {

        Object[] newElements = new Object[this.elements.length * 2 + 1];
        int indexToStart = newElements.length / 2 - (this.size / 2);
        int originalElementsHead = this.head;
        this.head = indexToStart;
        for (int i = originalElementsHead; i < this.tail; i++) {
            newElements[indexToStart++] = this.elements[i];
        }
        this.tail = indexToStart;
        this.elements = newElements;
    }

    private int checkIndex(int index) {

        int realIndex = this.head + index;

        if (realIndex < this.head || realIndex >= this.tail) {
            throw new IndexOutOfBoundsException();
        }

        return realIndex;
    }

    private void shiftRight(int realIndex) {

        for (int i = this.tail - 1; i >= realIndex; i--) {
            this.elements[i + 1] = this.elements[i];
        }
    }

    private void shiftLeft(int index) {

        int realIndex = this.checkIndex(index);

        for (int i = realIndex; i < this.tail ; i++) {
            this.elements[i] = this.elements[i + 1];
        }

        elements[this.tail] = null;
    }
}
