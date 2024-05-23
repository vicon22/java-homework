package com.isupov.homework.first.tree;

import java.util.*;

public class AVLTree<V> implements Tree<V> {

    private class Node<V> {

        private V data;
        int height;
        private Node<V> left;
        private Node<V> right;

        private Node(V data) {
            this.data = data;

        }
    }

    private Node<V> rootNode;

    public AVLTree() {
        rootNode = null;
    }

    private void updateHeight(Node<V> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(Node<V> node) {
        return node == null ? -1 : node.height;
    }

    private int getBalance(Node<V> node) {
        return node == null ? 0 :
                height(node.right) - height(node.left);
    }

    private Node<V> rotateRight(Node<V> y) {
        Node<V> x = y.left;
        Node<V> z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node<V> rotateLeft(Node<V> y) {
        Node<V> x = y.right;
        Node<V> z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;

    }

    @Override
    public void put(V value) {
        Objects.requireNonNull(value);
        rootNode = putRecursive(rootNode, value);
    }

    private Node<V> putRecursive(Node<V> current, V value) {
        if (current == null) {
            return new Node<>(value);
        }

        @SuppressWarnings("unchecked")
        Comparable<? super V> v = (Comparable<? super V>) value;
        int cmp = v.compareTo(current.data);
        if (cmp < 0) {
            current.left = putRecursive(current.left, value);
        } else if (cmp > 0) {
            current.right = putRecursive(current.right, value);
        } else {
            return current;
        }

        return rebalance(current);
    }

    @Override
    public boolean contains(V value) {

        @SuppressWarnings("unchecked")
        Comparable<? super V> v = (Comparable<? super V>) value;
        return containsRecursive(rootNode, v);
    }

    private boolean containsRecursive(Node<V> current, Comparable<? super V> v) {
        if (current == null) {
            return false;
        }
        int cmp = v.compareTo(current.data);
        if (cmp == 0) {
            return true;
        }
        return cmp < 0 ? containsRecursive(current.left, v) : containsRecursive(current.right, v);
    }

    @Override
    public void remove(V value) {
        @SuppressWarnings("unchecked")
        Comparable<? super V> v = (Comparable<? super V>) value;
        rootNode = removeRecursive(rootNode, v);
    }

    private Node<V> removeRecursive(Node<V> current, Comparable<? super V> v) {
        if (current == null) {
            return null;
        }
        int cmp = v.compareTo(current.data);
        if (cmp < 0) {
            current.left = removeRecursive(current.left, v);
        } else if (cmp > 0) {
            current.right = removeRecursive(current.right, v);
        } else {
            if (current.left == null || current.right == null) {
                current = (current.left == null) ?  current.right : current.left;
            } else {
                V smallestValue = findSmallestValue(current.right);
                current.data = smallestValue;
                current.right = removeRecursive(current.right, (Comparable<? super V>) smallestValue);
            }
        }

        if (current != null) {
            current = rebalance(current);
        }
        return current;
    }

    private Node<V> rebalance(Node<V> current) {
        updateHeight(current);
        int balance = getBalance(current);
        if (balance > 1) {
            if (height(current.right.right) > height(current.right.left)) {
                current = rotateLeft(current);
            } else {
                current.right = rotateRight(current.right);
                current = rotateLeft(current);
            }
        } else if (balance < -1) {
            if (height(current.left.left) > height(current.left.right)) {
                current = rotateRight(current);
            } else {
                current.left = rotateLeft(current.left);
                current = rotateRight(current);
            }
        }

        return current;
    }

    private V findSmallestValue(Node<V> current) {
        return current.left == null ? current.data : findSmallestValue(current.left);
    }

    @Override
    public void putAll(Tree<? extends V> tree) {
        tree.values().forEach(this::put);
    }

    @Override
    public List<V> values() {
        List<V> values = new ArrayList<>();
        traverseLevelOrder(rootNode, values);
        return values;
    }

    private void traverseInOrder(Node<V> current, List<V> values) {
        if (current != null) {
            traverseInOrder(current.left, values);
            values.add(current.data);
            System.out.println(current.data);
            traverseInOrder(current.right, values);
        }
    }

    private void traverseLevelOrder(Node<V> current, List<V> values) {
        if (current == null) {
            return;
        }

        Queue<Node<V>> nodes = new LinkedList<>();
        nodes.add(current);

        while (!nodes.isEmpty()) {

            Node<V> node = nodes.remove();
            values.add(node.data);

            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
    }
}
