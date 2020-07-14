package com.geekbrains.java.lesson12;

import java.lang.reflect.Field;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        ReflectionRepository<Animal> animals = new ReflectionRepository<>(Animal.class);
        try {
            animals.connect();
            animals.save(new Animal("Alex", "white"));
            animals.save(new Animal("Rufan", "brown"));
            animals.save(new Animal("Sergey", "yellow"));
            Animal animal1 = animals.getById(2);
            System.out.println(animal1.name);
            List<Animal> animalList = animals.getAll();
            for (Animal animal: animalList
                 ) {
                System.out.println(animal.id + " " + animal.name + " " + animal.color);
            }
            animals.deleteById(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            animals.disconnect();
        }
    }
}
