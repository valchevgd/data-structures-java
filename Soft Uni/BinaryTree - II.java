package solutions;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

    private int value;
    private BinaryTree left;
    private BinaryTree right;

    public BinaryTree(int key, BinaryTree first, BinaryTree second) {
        this.value = key;
        this.left = first;
        this.right = second;
    }

    public Integer findLowestCommonAncestor(int first, int second) {

        List<Integer> firstPath = findPath(first);
        List<Integer> secondPath = findPath(second);

        int smallerSize = Math.min(firstPath.size(), secondPath.size());
        int i = 0;
        for (; i < smallerSize; i++) {
            if (!firstPath.get(i).equals(secondPath.get(i))) {
                break;
            }
        }

        return firstPath.get(i - 1);
    }

    private List<Integer> findPath(int element) {
        List<Integer> result = new ArrayList<>();

        findNodePath(this, element, result);

        return result;
    }

    private boolean findNodePath(BinaryTree tree, int element, List<Integer> currentPath) {
        if (tree == null) {
            return false;
        }

        if (tree.value == element) {
            return true;
        }

        currentPath.add(tree.value);
        boolean leftResult = findNodePath(tree.left, element, currentPath);
        if (leftResult) {
            return true;
        }

        boolean rightResult = findNodePath(tree.right, element, currentPath);
        if (rightResult) {
            return true;
        }

        return false;
    }


    public List<Integer> topView() {

        List<Integer> result = new ArrayList<>();

        BinaryTree root = this;
        result.add(root.value);

        BinaryTree tree = root;
        while (tree.left != null) {
            result.add(tree.left.value);
            tree = tree.left;
        }

        tree = root;
        while (tree.right != null) {
            result.add(tree.right.value);
            tree = tree.right;
        }

        return result;
    }
}
