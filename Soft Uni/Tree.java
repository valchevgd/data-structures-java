package implementations;

import interfaces.AbstractTree;

import java.util.*;

public class Tree<E> implements AbstractTree<E> {

    private E key;
    private Tree<E> parent;
    private ArrayList<Tree<E>> children;

    public Tree(E key, Tree<E>...children) {
        this.key = key;
        this.children = new ArrayList<>();

        for (Tree<E> child : children) {
            this.children.add(child);
            child.parent = this;
        }
    }

    @Override
    public List<E> orderBfs() {
        List<E> result = new ArrayList<>();
        if (this.key == null) {
            return result;
        }
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);

        while (queue.size() > 0) {
            Tree<E> current = queue.poll();
            result.add(current.key);
            for (Tree<E> child : current.children) {
                queue.offer(child);
            }
        }

        return result;
    }

    @Override
    public List<E> orderDfs() {
        List<E> order = new ArrayList<>();

        this.doDfs(this, order);

        return order;
    }

    private void doDfs(Tree<E> tree, List<E> order) {
        for (Tree<E> child : tree.children) {
            this.doDfs(child, order);
        }

        order.add(tree.key);
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {

        Tree<E> parent = this.find(parentKey);

        if (parent != null) {
            parent.children.add(child);
            child.parent = parent;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void removeNode(E nodeKey) {
        Tree<E> nodeToRemove = this.find(nodeKey);

        if (nodeToRemove == null) {
            throw new IllegalArgumentException();
        }

        for (Tree<E> child : nodeToRemove.children) {
            child.parent = null;
        }

        nodeToRemove.children.clear();

        Tree<E> parent = nodeToRemove.parent;

        if (parent != null) {
            parent.children.remove(nodeToRemove);
        }

        nodeToRemove.key = null;
    }

    @Override
    public void swap(E firstKey, E secondKey) {

        Tree<E> firstNode = find(firstKey);
        Tree<E> secondNode = find(secondKey);

        if (firstNode == null || secondNode == null) {
            throw new IllegalArgumentException();
        } else {

            Tree<E> firstNodeParent = firstNode.parent;
            Tree<E> secondNodeParent = secondNode.parent;

            if (firstNodeParent == null) {
                swapRoot(secondNode);
                return;
            } else if (secondNodeParent == null) {
                swapRoot(firstNode);
                return;
            }

            int firstNodeIndex = firstNodeParent.children.indexOf(firstNode);
            int secondNodeIndex = secondNodeParent.children.indexOf(secondNode);

            firstNodeParent.children.set(firstNodeIndex, secondNode);
            secondNodeParent.children.set(secondNodeIndex, firstNode);

            secondNode.parent = firstNodeParent;
            firstNode.parent = secondNodeParent;
        }
    }

    private void swapRoot(Tree<E> node) {
        this.key = node.key;
        this.parent = null;
        this.children = node.children;
        node.parent = null;
    }

    private Tree<E> find(E key) {

        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);

        while (queue.size() > 0) {
            Tree<E> current = queue.poll();

            if (current.key.equals(key)) {
                return current;
            }

            for (Tree<E> child : current.children) {
                queue.offer(child);
                if (child.key.equals(key)) {
                    return child;
                }
            }
        }

        return null;
    }
}



