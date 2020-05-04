package implementations;

import interfaces.AbstractStack;

import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {

    private Node<E> top;
    private int size;

    private static class Node<E> {

        private E value;
        private Node<E> next;

        public Node(E element) {
            this.value = element;
            this.next = null;
        }
    }

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push(E element) {

        Node<E> node = this.top;
        Node<E> newNode = new Node<>(element);

        this.top = newNode;
        newNode.next = node;

        this.size++;

    }

    @Override
    public E pop() {

        if (this.size == 0) {
            throw  new IllegalStateException();
        }

        Node<E> top = this.top;
        this.top = top.next;

        this.size--;

        return top.value;
    }

    @Override
    public E peek() {

        if (this.size == 0) {
            throw  new IllegalStateException();
        }

        return this.top.value;
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

            Node<E> current = top;

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
