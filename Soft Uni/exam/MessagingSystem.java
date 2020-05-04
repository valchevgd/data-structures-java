package core;

import model.Message;
import model.TextMessage;
import shared.DataTransferSystem;

import java.util.*;

public class MessagingSystem implements DataTransferSystem {

    private List<TextMessage> elements;

    public MessagingSystem() {
        this.elements = new ArrayList<>();
    }

    @Override
    public void add(Message message) {
        int index = this.elements.indexOf(message);

        if (index != -1) {
            throw new IllegalArgumentException();
        }

        this.elements.add((TextMessage) message);
        this.heapifyDown();
    }

    private void heapifyDown() {

        int index = 0;

        while (getLeftChildIndex(index) < this.size() && isLess(index, getLeftChildIndex(index))) {
            int child = this.getLeftChildIndex(index);
            int rightChildIndex = getRightChildIndex(index);
            if (rightChildIndex < this.size() && isLess(child, rightChildIndex)) {
                child = rightChildIndex;
            }

            Collections.swap(this.elements, child, index);
            index = child;
        }
    }

    private int getLeftChildIndex(int index) {
        return (2 * index) + 1;
    }

    private int getRightChildIndex(int index) {
        return (2 * index) + 2;
    }

    private boolean isLess(int firstIndex, int secondIndex) {
        return this.elements.get(firstIndex).compareTo(this.elements.get(secondIndex)) < 0;
    }

    @Override
    public Message getByWeight(int weight) {

        for (TextMessage message : this.elements) {
            if (message.getWeight() == weight) {
                return message;
            }
        }

        throw new IllegalArgumentException();
    }

    @Override
    public Message getLightest() {

        ensureNotEmpty();

        int index = 0;

        while (getLeftChildIndex(index) < this.size()) {
            index = getLeftChildIndex(index);
        }

        return this.elements.get(index);
    }

    private void ensureNotEmpty() {
        if (this.size() == 0) {
            throw new IllegalStateException();
        }
    }

    @Override
    public Message getHeaviest() {

        ensureNotEmpty();

        int index = 0;

        while (getRightChildIndex(index) < this.size()) {
            index = getRightChildIndex(index);
        }

        return this.elements.get(index);
    }

    @Override
    public Message deleteLightest() {

        ensureNotEmpty();
        TextMessage lightest = (TextMessage) this.getLightest();
        int lastIndex = this. size() - 1;
        int messageIndex = this.elements.indexOf(lightest);
        Collections.swap(this.elements, messageIndex, lastIndex);

        this.elements.remove(lastIndex);
        this.heapifyUp();
        return lightest;
    }

    @Override
    public Message deleteHeaviest() {

        ensureNotEmpty();
        TextMessage heaviest = (TextMessage) this.getHeaviest();

        int lastIndex = this. size() - 1;
        int messageIndex = this.elements.indexOf(heaviest);
        Collections.swap(this.elements, messageIndex, lastIndex);

        this.elements.remove(heaviest);

        this.heapifyUp();
        return heaviest;
    }

    private void heapifyUp() {

        int index = this.size() - 1;

        while (index > 0 && isLess(index, getParentIndex(index))) {
            Collections.swap(this.elements, index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    @Override
    public Boolean contains(Message message) {

        return this.elements.indexOf(message) != -1;
    }

    @Override
    public List<Message> getOrderedByWeight() {
        return Collections.unmodifiableList(this.elements);
    }

    @Override
    public List<Message> getPostOrder() {

        List<Message> order = new ArrayList<>();

        Message message = this.elements.get(0);

        this.postOrder(message, order);

        return order;
    }

    private void postOrder(Message message, List<Message> order) {

        int messageIndex = this.elements.indexOf(message);
        int leftIndex = this.getLeftChildIndex(messageIndex);
        int rightIndex = this.getRightChildIndex(messageIndex);

        if (leftIndex < this.size()) {
            Message next = this.elements.get(leftIndex);
            this.postOrder(next, order);
        }

        if (rightIndex < this.size()) {
            Message next = this.elements.get(rightIndex);
            this.postOrder(next, order);
        }

        order.add(message);
    }


    @Override
    public List<Message> getPreOrder() {
        return Collections.unmodifiableList(this.elements);
    }

    @Override
    public List<Message> getInOrder() {

        return Collections.unmodifiableList(this.elements);
    }

    @Override
    public int size() {
        return this.elements.size();
    }
}
