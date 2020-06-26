package com.geekbrains.java.lesson5;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> fruits = new ArrayList<>();

    public Box(T... fruits) {
        for (T fruit : fruits
        ) {
            this.fruits.add(fruit);
        }
    }

    public void addToBox(T fruit) {
        fruits.add(fruit);
    }

    public float boxWeight() {
        float sum = 0;
        for (T fruit : fruits
        ) {
            sum += fruit.getWeight();
        }
        return sum;
    }

    public boolean compareToAnother(Box<?> another) {
        return Math.abs(this.boxWeight() - another.boxWeight()) < 0.00001;
    }

    public void putAllInAnother(Box<T> another) {
        another.fruits.addAll(this.fruits);
        this.fruits.clear();
    }

    public void printBox() {
        for (T fruit : fruits
        ) {
            System.out.println(fruit);
        }
    }
}
