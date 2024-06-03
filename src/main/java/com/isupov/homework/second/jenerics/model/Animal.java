package com.isupov.homework.second.jenerics.model;

public interface Animal extends Comparable<Animal>, Alive {

    void doSound();

    int getWeight();
}
