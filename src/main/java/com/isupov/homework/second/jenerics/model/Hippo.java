package com.isupov.homework.second.jenerics.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Hippo implements Animal {

    private int weight;

    @Override
    public void doSound() {
        System.out.println("Hippo sound");
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Animal o) {
        return Integer.compare(this.weight, o.getWeight());
    }
}
