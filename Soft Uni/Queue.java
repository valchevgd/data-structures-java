package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {

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

    public Queue() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void offer(E element) {

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
    public E poll() {

        if (this.isEmpty()) {
            throw  new IllegalStateException();
        }

        Node<E> temp = this.head.next;
        E value = this.head.value;
        this.head = temp;

        this.size--;

        return value;
    }

    @Override
    public E peek() {
        if (this.isEmpty()) {
            throw  new IllegalStateException();
        }

        return this.head.value;
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
                return this.current.next != null;
            }

            @Override
            public E next() {

                E value = this.current.value;

                this.current = this.current.next;

                return value;
            }
        };
    }
}
