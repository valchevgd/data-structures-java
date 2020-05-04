import models.Employee;

import java.util.LinkedList;

public class LinkedStack {

    private LinkedList<Character> stack;

    public LinkedStack() {
        stack = new LinkedList<>();
    }

    public void push(Character employee) {
        stack.push(employee);
    }

    public Character pop() {
        return stack.pop();
    }

    public Character peek() {
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void printStack() {
        for (Character employee : stack) {
            System.out.println(employee);
        }
    }
}
