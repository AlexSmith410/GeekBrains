package com.geekbrains.java.lesson5;

import java.util.ArrayList;

public class MainApp {
    public static void main(String[] args) {
//        testing task 3
        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();
        Orange orange1 = new Orange();
        Orange orange2 = new Orange();
        Box<Apple> bwa1 = new Box<>(apple1, apple2, apple3);
        Box<Apple> bwa2 = new Box<>(apple2, apple3);
        Box<Orange> bwo = new Box<>(orange1, orange2);

        System.out.println(bwa1.compareToAnother(bwo));

        bwa1.putAllInAnother(bwa2);
        bwa2.printBox();
        System.out.println();
        bwa1.printBox();
    }


    //    task 1
    public static <T> void swapArrayElements(T[] objects, int index1, int index2) {
        T temp = objects[index1];
        objects[index1] = objects[index2];
        objects[index2] = temp;
    }

    //    task 2
    public static <T> ArrayList<T> arrayToList(T[] array) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            arrayList.add(array[i]);
        }
        return arrayList;
    }
}
