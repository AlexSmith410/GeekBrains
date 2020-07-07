package com.geekbrains.java.lesson9;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinMax {

    private static Random random = new Random();
    private static final int N = 100000000;
    private static int[] array = new int[N];
    private static int THRESHOLD = 10000;

    static class FindMaxTask extends RecursiveTask<Integer> {

        private int start;
        private int end;

        public FindMaxTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int length = end - start;
            if (length < THRESHOLD) {
                return computeDirectly();
            }

            int offset = length / 2;

            FindMaxTask left = new FindMaxTask(start, start + offset);
            left.fork();
            FindMaxTask right = new FindMaxTask(start + offset, end);

            return Math.max(right.compute(), left.join());
        }

        private Integer computeDirectly() {
            int max = Integer.MIN_VALUE;
            for (int i = start; i < end; i++) {
                array[i] = random.nextInt(100000);
                if (max < array[i]) {
                    max = array[i];
                }
            }
            return max;
        }
    }

    public static void main(String[] args) {
//          Using ForkJoinPoll
//        long time = System.currentTimeMillis();
//        int max = new ForkJoinPool().invoke(new FindMaxTask(0, N));
//        System.out.println("Time: " + (System.currentTimeMillis() - time));
//        System.out.println("max = " + max);

        long time = System.currentTimeMillis();
        Integer[] array = new Integer[100000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100000);
        }
//          Using ParallelStream
        System.out.println(Arrays.asList(array).parallelStream().max(Integer::compareTo).get());
        System.out.println("Time: " + (System.currentTimeMillis() - time));

//          Using Stream
        System.out.println(Arrays.asList(array).stream().max(Integer::compareTo).get());
        System.out.println("Time: " + (System.currentTimeMillis() - time));

//            Simple one-threaded search
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max)
                max = array[i];
        }
        System.out.println("Time: " + (System.currentTimeMillis() - time));
        System.out.println(max);
    }
//                   Time in millis
//    ForkJoinPool:      9673
//    Stream:            3114
//    ParallelStream:    2885
//    OneThread:         2864
}
