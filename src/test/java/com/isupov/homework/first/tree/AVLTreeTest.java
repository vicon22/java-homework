package com.isupov.homework.first.tree;

import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AVLTreeTest {

    private Tree<Integer> tree;

    @BeforeEach
    public void setUp() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        tree = new AVLTree<>();
        integerList.forEach(tree::put);
    }

    @Test
    public void balanceTest() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4);
        Tree<Integer> avlTree = new AVLTree<>();
        integerList.forEach(avlTree::put);
        System.out.println("avl:" + avlTree.values());

        Tree<Integer> binarySearchTree = new BinarySearchTree<>();
        integerList.forEach(binarySearchTree::put);
        System.out.println("bst:" + binarySearchTree.values());
    }

    @Test
    public void balanceMoreNumbersTest() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        Tree<Integer> avlTree = new AVLTree<>();
        integerList.forEach(avlTree::put);
        System.out.println("avl:" + avlTree.values());

        Tree<Integer> binarySearchTree = new BinarySearchTree<>();
        integerList.forEach(binarySearchTree::put);
        System.out.println("bst:" + binarySearchTree.values());
    }

    @Test
    public void removeBalanceTest() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        Tree<Integer> avlTree = new AVLTree<>();
        integerList.forEach(avlTree::put);
        avlTree.remove(4);
        avlTree.remove(7);
        System.out.println("avl:" + avlTree.values());

        Tree<Integer> binarySearchTree = new BinarySearchTree<>();
        integerList.forEach(binarySearchTree::put);
        binarySearchTree.remove(4);
        binarySearchTree.remove(7);
        System.out.println("bst:" + binarySearchTree.values());
    }

    @Test
    public void balanceNegativeNumbersTest() {
        List<Integer> integerList = Arrays.asList(-1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12);
        Tree<Integer> avlTree = new AVLTree<>();
        integerList.forEach(avlTree::put);
        System.out.println("avl:" + avlTree.values());

        Tree<Integer> binarySearchTree = new BinarySearchTree<>();
        integerList.forEach(binarySearchTree::put);
        System.out.println("bst:" + binarySearchTree.values());
    }

    @Test
    public void balanceRandomNumbersTest() {
        List<Integer> integerList = new Random().ints().limit(100)
                .sorted()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        Tree<Integer> avlTree = new AVLTree<>();
        integerList.forEach(avlTree::put);
        System.out.println("avl:" + avlTree.values());

        Tree<Integer> binarySearchTree = new BinarySearchTree<>();
        integerList.forEach(binarySearchTree::put);
        System.out.println("bst:" + binarySearchTree.values());
    }

    @Test
    public void putTest() {
        List<Integer> integerList = Arrays.asList(131, 2, 8, 75, -15, 0 ,34, 56, 65);
        Tree<Integer> tree = new AVLTree<>();
        integerList.forEach(tree::put);
        Assert.assertEquals(tree.values().size(), integerList.size());
    }

    @Test
    public void containsTrueTest() {

        Assert.assertTrue(tree.contains(1));
        Assert.assertTrue(tree.contains(2));
        Assert.assertTrue(tree.contains(10));
        Assert.assertTrue(tree.contains(12));
        Assert.assertTrue(tree.contains(7));
    }

    @Test
    public void containsFalseTest() {
        Assert.assertFalse(tree.contains(132));
        Assert.assertFalse(tree.contains(22));
        Assert.assertFalse(tree.contains(-17));
        Assert.assertFalse(tree.contains(99));
        Assert.assertFalse(tree.contains(-1));
    }

    @Test
    public void removeActualValuesTest() {
        Assert.assertTrue(tree.contains(1));
        tree.remove(1);
        Assert.assertFalse(tree.contains(1));

        Assert.assertTrue(tree.contains(8));
        tree.remove(8);
        Assert.assertFalse(tree.contains(8));

        Assert.assertEquals(tree.values().size(), 10);
    }

    @Test
    public void removeFakeValuesTest() {
        tree.remove(133);
        tree.remove(99);
        tree.remove(100);
        tree.remove(33);
        tree.remove(1111);

        Assert.assertEquals(tree.values().size(), 12);
    }

    @Test
    public void putAllTest() {
        List<Integer> integerList = Arrays.asList(13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
        Tree<Integer> tree2 = new AVLTree<>();
        integerList.forEach(tree2::put);

        tree.putAll(tree2);

        Assert.assertEquals(tree.values().size(), 24);
        Assert.assertTrue(tree.contains(1));
        Assert.assertTrue(tree.contains(24));

        System.out.println(tree.values());
    }

    public void valuesTest() {
        Assert.assertEquals(tree.values().size(), 5);
    }
}