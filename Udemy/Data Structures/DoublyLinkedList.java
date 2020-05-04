package doublylinkedlist;

import models.Employee;
import models.EmployeeNode;

public class DoublyLinkedList {

    private EmployeeNode head;
    private EmployeeNode tail;
    private int size;

    public void addToFront(Employee employee) {
        EmployeeNode node = new EmployeeNode(employee);
        node.setNext(head);

        if (head == null) {
            tail = node;
        } else {
            head.setPrevious(node);
        }

        head = node;
        size++;
    }

    public boolean addBefore(Employee existingEmployee, Employee newEmployee) {

        if (isEmpty()) {
            return false;
        }

        EmployeeNode current = head;

        while (current != null && !current.getEmployee().equals(existingEmployee)) {
            current = current.getNext();
        }

        if (current == null) {
            return false;
        }

        EmployeeNode newEmployeeNode = new EmployeeNode(newEmployee);

        newEmployeeNode.setPrevious(current.getPrevious());
        newEmployeeNode.setNext(current);
        current.setPrevious(newEmployeeNode);

        if (current.getPrevious() != null) {
            newEmployeeNode.getPrevious().setNext(newEmployeeNode);
        } else {
            head = newEmployeeNode;
        }

        size++;

        return true;
    }

    public void addToEnd(Employee employee) {

        EmployeeNode node = new EmployeeNode(employee);

        if (tail == null) {
            head = node;
        } else {
            tail.setNext(node);
            node.setPrevious(tail);
        }

        tail = node;
        size++;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void printList() {
        EmployeeNode current = head;
        System.out.print("HEAD -> ");
        while (current != null) {
            System.out.print(current);
            System.out.print(" <=> ");
            current = current.getNext();
        }

        System.out.println("null");
    }

    public Employee removeFromFront() {
        if (isEmpty()) {
            return null;
        }

        EmployeeNode removeNode = head;

        if (head.getNext() == null) {
            tail = null;
        } else {
            head.getNext().setPrevious(null);
        }

        head = head.getNext();
        size--;
        removeNode.setNext(null);
        return removeNode.getEmployee();
    }

    public Employee removeFromEnd() {
        if (isEmpty()) {
            return null;
        }

        EmployeeNode removeNode = tail;

        if (tail.getPrevious() == null) {
            head = null;
        } else {
            tail.getPrevious().setNext(null);
        }

        tail = tail.getPrevious();
        removeNode.setPrevious(null);
        size--;

        return removeNode.getEmployee();
    }
}
