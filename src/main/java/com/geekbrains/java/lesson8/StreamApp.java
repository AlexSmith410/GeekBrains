package com.geekbrains.java.lesson8;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApp {
    public static void main(String[] args) {
//        task 1
        String[] array = {"f", "fl", "flo", "flow", "flowe", "flower"};
        String str = "";
        for (int i = 0; i < 100; i++) {
            str += array[i % 6] + " ";
        }
        String result = Stream.of(str.split(" ")).filter(s -> s.length() > 5).collect(Collectors.joining(" "));
        System.out.println(result);
//        task 2
        String[][] str2D = {{"a", "b", "c", "d", "e"}, {"f", "b", "g", "d", "e"}, {"g", "e", "r", "d", "b"}, {"z", "x", "c", "q", "w"}, {"x", "w", "c", "d", "e"},};
        System.out.println(Arrays.stream(str2D).flatMap(Arrays::stream).distinct().collect(Collectors.joining(", ", "Уникальные слова: ", ".")));
//        task 3
        IntStream rangedIntStream = IntStream.rangeClosed(100, 200);
        System.out.println(rangedIntStream.filter(n -> n % 2 == 0).sum());
//        task 4
        System.out.println(Arrays.stream(array).mapToInt(String::length).sum());
//        task 5
        String[] array2 = {"b", "c", "d", "a", "a2"};
        Arrays.stream(array2).sorted().limit(3).forEach(System.out::println);
    }
}
