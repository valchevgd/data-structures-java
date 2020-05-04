package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {

    private Node<E> head;
    private int size;

    private static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E element) {
            this.value = element;
            this.next = null;
        }
    }

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void addFirst(E element) {

        Node<E> oldFirst = this.head;
        this.head = new Node<>(element);
        this.head.next = oldFirst;

        this.size++;
    }

    @Override
    public void addLast(E element) {
        if (isEmpty()) {
            this.head = new Node<>(element);
        } else {
            Node<E> current = this.head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = new Node<>(element);
        }

        this.size++;
    }

    @Override
    public E removeFirst() {

        if (isEmpty()) {

            throw new IllegalStateException();
        }

        Node<E> toRemove = this.head;
        this.head = toRemove.next;

        this.size--;

        return toRemove.value;
    }

    @Override
    public E removeLast() {

        Node<E> current = this.head;
        Node<E> last = current.next;

        while (last.next != null) {
            current = last;
            last = last.next;
        }

        current.next = null;

        this.size--;

        return last.value;
    }

    @Override
    public E getFirst() {

        if (isEmpty()) {
            throw new IllegalStateException();
        }

        return this.head.value;
    }

    @Override
    public E getLast() {
        Node<E> current = this.head;

        while (current.next != null) {
            current = current.next;
        }

        return current.value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = head;
            @Override
            public boolean hasNext() {
                return current.next != null;
            }

            @Override
            public E next() {

                E value = current.value;

                current = current.next;

                return value;
            }
        };
    }
}
