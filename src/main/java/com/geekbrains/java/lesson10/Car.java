package com.geekbrains.java.lesson10;

import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {
    private static int CARS_COUNT;

    private Race race;
    private int speed;
    private String name;
    private static AtomicInteger allReady = new AtomicInteger(0); //changed here
    private static final Object mon = new Object(); //changed here

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //changed here
        synchronized (mon) {
            allReady.addAndGet(1);
            if (allReady.get() != CARS_COUNT) {
                try {
                    mon.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                mon.notifyAll();
            }
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).overcome(this);
        }
        //changed here
        if (allReady.decrementAndGet() == CARS_COUNT - 1) {
            System.out.println(this.name + " ПОБЕДИЛ!");
        }

        if (allReady.get() == 0)
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}