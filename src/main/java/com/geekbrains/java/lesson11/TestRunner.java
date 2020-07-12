package com.geekbrains.java.lesson11;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestRunner {
    public static void main(String[] args) throws Exception {
        Class c = Tests.class;
        runTests(c);
    }

    public static void runTests(Class c) throws Exception {
        Method[] methods = c.getDeclaredMethods();
        List<Method> testMethods = new ArrayList<Method>();
        List<Method> initMethod = new ArrayList<Method>();
        List<Method> endMethod = new ArrayList<Method>();
        for (int i = 0; i < methods.length; i++) {
            if ((methods[i].isAnnotationPresent(Test.class))) {
                testMethods.add(methods[i]);
            }
            if ((methods[i].isAnnotationPresent(BeforeSuit.class))) {
                initMethod.add(methods[i]);
            }
            if ((methods[i].isAnnotationPresent(AfterSuit.class))) {
                endMethod.add(methods[i]);
            }
        }
        if (endMethod.size() > 1 || initMethod.size() > 1)
            throw new RuntimeException();

        if (initMethod.size() != 0)
            System.out.println(initMethod.get(0).getName());

        Collections.sort(testMethods, (o1, o2) -> {
            if (o1.getAnnotation(Test.class).value() > o2.getAnnotation(Test.class).value()) {
                return -1;
            } else {
                return 1;
            }
        });

        for (Method m : testMethods
        ) {
            System.out.println(m.getName());
        }

        if (endMethod.size() != 0)
            System.out.println(endMethod.get(0).getName());
    }
}

//committed