package com.geekbrains.java.lesson6;

import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {
//        testing task 1
        String[] array = {"cat", "car", "dog", "cat", "apple", "car", "rain", "appl", "apple", "rain", "star", "zCheckLastWord"};
//        getUniqueWords(array);

//        tesing task 2
        PhoneBook pb = new PhoneBook();
        pb.add("123", "Ivanov");
        pb.add("322", "Berezin");
        pb.add("777", "Sidorov");
        pb.add("321", "Ivanov");
        pb.add("909", "Kozlov");
        pb.add("009", "Kozlov");
        pb.add("1000", "Petrov");

        pb.get("Ivanov");
        pb.get("Berezin");
        pb.get("Kozlov");
        System.out.println();
        pb.add("1000", "Nikonov");
        pb.get("Petrov");
    }

//    task 1
    public static void getUniqueWords(String[] starr) {
        if (starr.length == 0){
            System.out.println("This array is empty");
            return;
        }
        if (starr.length == 1){
            System.out.println(starr[0] + ": 1");
            return;
        }
        Arrays.sort(starr);
        int count = 1;
        for (int i = 1; i < starr.length; i++) {
            if (starr[i] != starr[i - 1]){
                System.out.println(starr[i - 1] + ": " + count);
                count = 1;
                continue;
            }
            count++;
        }
        System.out.println(starr[starr.length - 1] + ": " + count);
    }

}
