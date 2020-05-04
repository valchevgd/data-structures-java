import models.Employee;

import java.util.EmptyStackException;

public class ArrayStack {

    private Employee[] stack;
    private int top;

    public ArrayStack(int capacity) {
        stack = new Employee[capacity];
    }

    public void push(Employee employee) {

        if (top == stack.length) {
            resize();
        }

        stack[top++] = employee;
    }

    public Employee pop() {

        if (isEmpty()) {
            throw new EmptyStackException();
        }

        Employee employee = stack[--top];
        stack[top] = null;

        return employee;
    }

    public Employee peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return stack[top - 1];
    }

    public int size() {
        return top;
    }

    public boolean isEmpty() {
        return top == 0;
    }

    private void resize() {
        Employee[] newArray = new Employee[stack.length * 2];
        System.arraycopy(stack, 0, newArray, 0, stack.length);
        stack = newArray;
    }
}
