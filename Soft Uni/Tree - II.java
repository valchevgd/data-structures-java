package implementations;

import interfaces.AbstractTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {

    private E value;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E value, Tree<E>... children) {
        this.value = value;
        this.children = new ArrayList<>();
        for (Tree<E> child : children) {
            child.setParent(this);
            this.children.add(child);
        }
    }

    public Tree() {
        this.children = new ArrayList<>();
    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public E getKey() {
        return this.value;
    }

    @Override
    public String getAsString() {

        StringBuilder builder = new StringBuilder();

        fillBuilder(this, builder, 0);

        return builder.toString().trim();
    }

    private void fillBuilder(Tree<E> eTree, StringBuilder builder, int spaces) {

        for (int i = 0; i < spaces; i++) {
            builder.append(" ");
        }
        builder.append(eTree.getKey());
        builder.append(System.lineSeparator());

        for (Tree<E> child : eTree.children) {
            this.fillBuilder(child, builder, spaces + 2);
        }
    }

    @Override
    public List<E> getLeafKeys() {

        List<E> leafKeys = new ArrayList<>();

        this.findLeafs(this, leafKeys);

        return leafKeys;
    }

    private void findLeafs(Tree<E> tree, List<E> leafKeys) {

        if (tree.children.isEmpty()) {
            leafKeys.add(tree.getKey());
            return;
        }

        for (Tree<E> child : tree.children) {
            this.findLeafs(child, leafKeys);
        }
    }

    @Override
    public List<E> getMiddleKeys() {

        List<E> middleNodes = new ArrayList<>();

        this.findMiddleNodes(this, middleNodes);

        return middleNodes;
    }

    private void findMiddleNodes(Tree<E> tree, List<E> middleNodes) {

        if (tree.parent != null && !tree.children.isEmpty()) {
            middleNodes.add(tree.getKey());
        }

        for (Tree<E> child : tree.children) {
            this.findMiddleNodes(child, middleNodes);
        }
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
        return this.findDeepestNode(this);
    }

    private Tree<E> findDeepestNode(Tree<E> tree) {

        int maxPath[] = new int[1];
        int max = 0;
        List<Tree<E>> deepestNode = new ArrayList<>();

        deepestNode.add(new Tree<E>());

        this.checkPath(deepestNode, maxPath, max, this);

        return deepestNode.get(0);
    }

    private void checkPath(List<Tree<E>> deepestNode, int[] maxPath, int max, Tree<E> tree) {

        if (max > maxPath[0]) {
            maxPath[0] = max;
            deepestNode.set(0, tree);
        }

        for (Tree<E> child : tree.children) {
            checkPath(deepestNode, maxPath, max + 1, child);
        }
    }

    @Override
    public List<E> getLongestPath() {
        List<E> longestPath = new ArrayList<>();

        Tree<E> node = this.findDeepestNode(this);

        while (node.parent != null) {
            longestPath.add(node.getKey());
            node = node.parent;
        }

        longestPath.add(node.getKey());

        Collections.reverse(longestPath);

        return longestPath;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {

        List<Tree<E>> lastsNodes = new ArrayList<>();
        this.getPathsSums(this, 0, lastsNodes, sum);

        List<List<E>> allPaths = new ArrayList<>();

        for (Tree<E> node : lastsNodes) {

            List<E> path = new ArrayList<>();

            while (node.parent != null) {
                path.add(node.getKey());
                node = node.parent;
            }
            path.add(node.getKey());
            Collections.reverse(path);
            allPaths.add(path);
        }

        return allPaths;
    }

    private void getPathsSums(Tree<E> tree, int sum, List<Tree<E>> lastsNodes, int originalSum) {

        sum += (int)tree.getKey();

        for (Tree<E> child : tree.children) {
            this.getPathsSums(child, sum, lastsNodes, originalSum);
        }

        if (sum == originalSum) {
            lastsNodes.add(tree);
        }
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        return null;
    }
}



