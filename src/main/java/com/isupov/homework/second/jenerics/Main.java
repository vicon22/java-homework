package com.isupov.homework.second.jenerics;

import com.isupov.homework.first.tree.Tree;
import com.isupov.homework.second.jenerics.model.*;

public class Main {

    public static void main(String[] args) {

        Elephant elephant = new Elephant(10_000);
        Hippo hippo = new Hippo(8000);
        Tiger tiger = new Tiger(1000);
        Zebra zebra = new Zebra(700);

        Tree<Animal> animalTree = new AnimalTree<>();

//        Tree<NotAnimal> notAnimalTree = new AnimalTree<>(); //Not correct
//        AnimalTree<Alive> animalTree2; //Not correct
//        AnimalTree<NotAnimal> animalTree3; //Not correct
//        AnimalTree<Hippo> animalTree4; //Correct

        animalTree.put(elephant);
        animalTree.put(hippo);
        animalTree.put(tiger);
        animalTree.put(zebra);

        System.out.println(animalTree.values());
    }
}
