package com.geekbrains.java.lesson6;

import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    private Map<String, String> numbers = new HashMap<>();

    public void add(String number, String name){
        numbers.put(number, name);
    }

    public void get(String name){
//        HashMap<String,Object> map = new HashMap<String,Object>();
        for (Map.Entry<String, String> pair : numbers.entrySet()) {
            if (name.equals(pair.getValue())) {
                System.out.println(pair.getKey());
            }
        }
    }
}
