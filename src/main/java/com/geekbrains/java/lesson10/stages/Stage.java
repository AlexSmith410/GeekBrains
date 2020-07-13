package com.geekbrains.java.lesson10.stages;

import com.geekbrains.java.lesson10.Car;

public abstract class Stage {
    int length;
    String description;

    public String getDescription() {
        return description;
    }

    public abstract void overcome(Car c);
}
