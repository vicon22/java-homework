package com.isupov.homework.first.tree;

import java.util.Collection;
import java.util.List;

public interface Tree<V> {

    void put(V value);
    boolean contains(V value);
    void remove(V value);
    void putAll(Tree<? extends V> tree);
    List<V> values();
}
