package com.isupov.homework.first.tree;

import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class BinarySearchTreeTest {

    private Tree<Integer> tree;

    @BeforeEach
    public void setUp() {
        List<Integer> integerList = Arrays.asList(131, 2, 8, 45, 97);
        tree = new BinarySearchTree<>();
        integerList.forEach(tree::put);
    }


    @Test
    public void putTest() {
        List<Integer> integerList = Arrays.asList(131, 2, 8, 75, -15, 0 ,34, 56, 65);
        Tree<Integer> tree = new BinarySearchTree<>();
        integerList.forEach(tree::put);
        Assert.assertEquals(tree.values().size(), integerList.size());
    }

    @Test
    public void containsTrueTest() {
        Assert.assertTrue(tree.contains(131));
        Assert.assertTrue(tree.contains(2));
        Assert.assertTrue(tree.contains(97));
        Assert.assertTrue(tree.contains(45));
        Assert.assertTrue(tree.contains(97));
    }

    @Test
    public void containsFalseTest() {
        Assert.assertFalse(tree.contains(132));
        Assert.assertFalse(tree.contains(1));
        Assert.assertFalse(tree.contains(-17));
        Assert.assertFalse(tree.contains(99));
        Assert.assertFalse(tree.contains(-1));
    }

    @Test
    public void removeActualValuesTest() {
        Assert.assertTrue(tree.contains(131));
        tree.remove(131);
        Assert.assertFalse(tree.contains(131));

        Assert.assertTrue(tree.contains(8));
        tree.remove(8);
        Assert.assertFalse(tree.contains(8));

        Assert.assertEquals(tree.values().size(), 3);
    }

    @Test
    public void removeFakeValuesTest() {
        tree.remove(133);
        tree.remove(99);
        tree.remove(100);
        tree.remove(33);
        tree.remove(1111);

        Assert.assertEquals(tree.values().size(), 5);
    }

    @Test
    public void putAllTest() {
        List<Integer> integerList = Arrays.asList(65, 45, 82, 1, 556);
        Tree<Integer> tree2 = new BinarySearchTree<>();
        integerList.forEach(tree2::put);

        tree.putAll(tree2);

        Assert.assertEquals(tree.values().size(), 9);
        Assert.assertTrue(tree.contains(1));
        Assert.assertTrue(tree.contains(556));
    }

    public void valuesTest() {
        Assert.assertEquals(tree.values().size(), 5);
    }
}