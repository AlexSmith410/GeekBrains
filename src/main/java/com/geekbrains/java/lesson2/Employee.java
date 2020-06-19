package com.geekbrains.java.lesson2;

public class Employee {
    private String name;
    private int age;
    private String email;
    private String position;

    public Employee() {
        name = "Unknown";
        age = 1;
        email = "changemepls@gmail.com";
        position = "Manager";
    }

    public Employee(String name, int age, String email, String position) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if (age > 18)
            this.age = age;
        else
            System.out.println("Invalid age");
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void getInfo() {
        System.out.println("Имя: " + name);
        System.out.println("Возраст: " + age);
        System.out.println("Должность: " + position);
        System.out.println("Электронная почта: " + email);
    }
}
