package com.geekbrains.java.lesson11;

import java.lang.reflect.Method;

public class TestRunner {
    public static void main(String[] args) {
        Class c = Tests.class;
//        Method[] methods = c.getDeclaredMethods();
        runTests(c);
    }

    public static void runTests(Class c){
        if (!c.isAnnotationPresent(Test.class)) {
            throw new RuntimeException("Unable to create table for class " + c.getSimpleName());
        }
    }
}
