package com.geekbrains.java.lesson12;

@DbTable(name = "student")
public class Student {
    @DbId
    int id;

    @DbColumn
    String name;

    public Student(String name){
        this.name = name;
    }

    public Student(){

    }
}
