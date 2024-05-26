package com.isupov.homework.first.tree;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.*;

public class RBTree implements Tree<Integer> {

    @Builder
    @EqualsAndHashCode
    public static class Node {

        private static final boolean RED = false;
        private static final boolean BLACK = true;

        public int data;
        public boolean color;
        public Node parent;
        public Node left;
        public Node right;
    }

    private final Node nullNode;
    private Node root;
    
    public RBTree() {
        nullNode = createNewNode(-1, Node.BLACK);
        nullNode.parent = nullNode;

        root = nullNode;
    }

    @Override
    public void put(Integer value){
        Objects.requireNonNull(value);
        Node node = createNewNode(value, Node.RED);
        Node parent = getParent(node);
        node.parent = parent;

        if (isNullNode(parent)) {
            root = node;
        } else if (node.data < parent.data) {
            parent.left = node;
        } else {
            parent.right = node;
        }

        putFixup(node);
    }

    private Node getParent(Node node) {
        Node current = root;
        Node parent = nullNode;
        while (!isNullNode(current)) {
            parent = current;
            if (node.data < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return parent;
    }

    private Node createNewNode(Integer initData, boolean initColor) {
        return Node.builder()
                .data(initData)
                .color(initColor)
                .left(nullNode)
                .right(nullNode)
                .build();
    }

    private void putFixup(Node node){
        Node y;
        while (node.parent.color == Node.RED) {
            if (node.parent.equals(node.parent.parent.left)) {
                y = node.parent.parent.right;
                if (y.color == Node.RED) {
                    node.parent.color = Node.BLACK;
                    y.color = Node.BLACK;
                    node.parent.parent.color = Node.RED;
                    node = node.parent.parent;
                } else {
                    if (node.equals(node.parent.right)) {
                        node = node.parent;
                        LeftRotate(node);
                    }
                    node.parent.color = Node.BLACK;
                    node.parent.parent.color = Node.RED;
                    RightRotate(node.parent.parent);
                }
            } else {
                y = node.parent.parent.left;
                if (y.color == Node.RED) {
                    node.parent.color = Node.BLACK;
                    y.color = Node.BLACK;
                    node.parent.parent.color = Node.RED;
                    node = node.parent.parent;
                } else {
                    if (node.equals(node.parent.left)) {
                        node = node.parent;
                        RightRotate(node);
                    }
                    node.parent.color = Node.BLACK;
                    node.parent.parent.color = Node.RED;
                    LeftRotate(node.parent.parent);
                }
            }
        }

        root.color =  Node.BLACK;
    }

    private void LeftRotate(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;
        if (!isNullNode(rightChild.left)) {
            rightChild.left.parent = node;
        }
        rightChild.parent = node.parent;

        if (isNullNode(node.parent)) {
            root = rightChild;
        } else if (node.equals(node.parent.left)) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }

        rightChild.left = node;
        node.parent = rightChild;

    }

    private void RightRotate(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;
        if (!isNullNode(leftChild.right)) {
            leftChild.right.parent = node;
        }
        leftChild.parent = node.parent;

        if (isNullNode(node.parent)) {
            root = leftChild;
        } else if (node.equals(node.parent.left)) {
            node.parent.left = leftChild;
        } else {
            node.parent.right = leftChild;
        }

        leftChild.right = node;
        node.parent = leftChild;
    }

    @Override
    public boolean contains(Integer z) {
        Node foundedNode = recursiveSearch(root, z);
        return !isNullNode(foundedNode);
    }

    private Node recursiveSearch(Node node, Integer value) {
        if (isNullNode(node)) {
            return nullNode;
        }

        if (node.data > value) {
            return recursiveSearch(node.left, value);
        } else if (node.data < value) {
            return recursiveSearch(node.right, value);
        } else {
            return node;
        }
    }

    @Override
    public void remove(Integer value) {
        Node foundedNode = recursiveSearch(root, value);
        if (!isNullNode(foundedNode)) {
            remove(foundedNode);
        }
    }

    @Override
    public void putAll(Tree<? extends Integer> tree) {
        tree.values().forEach(this::put);
    }
    
    private boolean isNullNode(Node node) {
        return nullNode.equals(node);
    }

    private void remove(Node foundedNode) {
        Node x, y;

        y = (isNullNode(foundedNode.left) || isNullNode(foundedNode.right))
                ? foundedNode
                : treeSuccessor(foundedNode);

        x = (!isNullNode(y.left)) ? y.left : y.right;

        x.parent = y.parent;
        if (isNullNode(y.parent)) {
            root = foundedNode;
        } else if (y.equals(y.parent.left)) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }

        if (!foundedNode.equals(y)) {
            foundedNode.data = y.data;
        }

        if (y.color == Node.BLACK) {
            removeFixup(x);
        }

    }

    private void removeFixup(Node node) {
        Node w;
        while (!node.equals(root) && node.color == Node.BLACK) {
            if (node.equals(node.parent.left)) {
                w = node.parent.right;
                if (w.color == Node.RED) {
                    w.color = Node.BLACK;
                    w.parent.color = Node.RED;
                    LeftRotate(node.parent);
                    w = node.parent.right;
                }

                if (w.left.color == Node.BLACK && w.right.color == Node.BLACK) {
                    w.color = Node.RED;
                    node = node.parent;
                } else {
                    if (w.right.color == Node.BLACK) {
                        w.left.color = Node.RED;
                        w.color =  Node.BLACK;
                        RightRotate(node);
                        w = node.parent.right;
                    }

                    w.color = node.parent.color;
                    node.parent.color = Node.BLACK;
                    w.right.color = Node.BLACK;
                    LeftRotate(node.parent);
                    node = root;
                }
            } else {
                w = node.parent.left;
                if (w.color == Node.RED) {
                    w.color =  Node.BLACK;
                    w.parent.color = Node.RED;
                    LeftRotate(node.parent);
                    w = node.parent.right;
                }

                if (w.left.color == Node.BLACK && w.right.color == Node.BLACK) {
                    w.color = Node.RED;
                    node = node.parent;
                } else {
                    if (w.right.color == Node.BLACK) {
                        w.left.color = Node.RED;
                        w.color = Node.BLACK;
                        LeftRotate(node);
                        w = node.parent.right;
                    }

                    w.color = node.parent.color;
                    node.parent.color =  Node.BLACK;
                    w.right.color = Node.BLACK;
                    RightRotate(node.parent);
                    node = root;
                }
            }
        }

        node.color =  Node.BLACK;
    }
    
    private Node treeSuccessor(Node node) {
        if (!isNullNode(node.right)) {
            return treeMin(node.right);
        }

        Node parent = node.parent;
        while (!isNullNode(parent) && node.equals(parent.right)) {
            node = parent;
            parent = parent.parent;
        }

        return parent;
    }
    
    private Node treeMin(Node node) {
        while (!isNullNode(node.right)) {
            node = node.right;
        }

        return node;
    }

    public void display() {
        StringBuffer sb = new StringBuffer();
        displayRecursive(root, sb);
        System.out.println(sb);
    }

    private void displayRecursive(Node current, StringBuffer sb){

        if (current.equals(nullNode)) {
            sb.append("nullB");
        } else {
            sb.append("(")
                    .append(current.data)
                    .append((current.color) ? "B" : "R")
                    .append(",");
            displayRecursive(current.left, sb);
            sb.append(",");
            displayRecursive(current.right, sb);
            sb.append(")");
        }
    }

    @Override
    public List<Integer> values() {
        List<Integer> values = new ArrayList<>();
        traverseLevelOrder(root, values);
        return values;
    }

    private void traverseLevelOrder(Node current, List<Integer> values) {
        if (current == null) {
            return;
        }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(current);

        while (!nodes.isEmpty()) {
            Node node = nodes.remove();
            values.add(node.data);

            if (!isNullNode(node.left)) {
                nodes.add(node.left);
            }
            if (!isNullNode(node.right)) {
                nodes.add(node.right);
            }
        }
    }
}
