package com.geekbrains.java.lesson7;

import java.util.*;

public class MainApp {
    public static void main(String[] args) {
//        task 1, 2
//        ArrayList<Integer> arrayList = new ArrayList<>(Collections.nCopies(100, 0));
//        LinkedList<Integer> linkedList = new LinkedList<>(Collections.nCopies(100, 0));
//        long time = System.currentTimeMillis();
//        for (int i = 0; i < 50000; i++) {
//            linkedList.remove(linkedList.size() / 2);
//        }
//        System.out.println("Time: " + (System.currentTimeMillis() - time));

        /* task 1
               10     100     10000    100000
ArrayList:      1      1        1         1
LinkedList:     0      2        69       744

           task 2
               100     10000    100000
ArrayList:      0        2        139
LinkedList:     0        28       2757
            */

//        task 3
        ArrayList<MyEntry> myList = new ArrayList<>();
        HashMap<Integer, Integer> myMap = new HashMap<>();
        for (int i = 0; i < 50000; i++) {
            myList.add(new MyEntry(i, i + 1));
            myMap.put(i, i + 1);
        }
        Random random = new Random();
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            int randNum = random.nextInt(50000);
//            for (int j = 0; j < myList.size(); j++) {
//                if (myList.get(j).getKey().equals(randNum))
//                    myList.get(j).getValue();
//            }
            myMap.get(randNum);
        }
        System.out.println("Time: " + (System.currentTimeMillis() - time));
        /* task 3
        ArrayList: 15719
        HashMap: 11
         */
    }
}
