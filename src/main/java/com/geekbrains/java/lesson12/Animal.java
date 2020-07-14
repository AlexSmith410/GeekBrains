package com.geekbrains.java.lesson12;

@DbTable(name = "animal")
public class Animal {
    @DbId
    int id;

    @DbColumn
    String name;

    @DbColumn
    String color;

    public Animal(String name, String color){
        this.name = name;
        this.color = color;
    }

    public Animal(){
    }
}
