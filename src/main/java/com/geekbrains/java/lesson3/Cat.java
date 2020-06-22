package com.geekbrains.java.lesson3;

public class Cat implements Participant {
    private int maxHeight;
    private int maxLength;

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public Cat(int maxHeight, int maxLength) {
        this.maxHeight = maxHeight;
        this.maxLength = maxLength;
    }


    @Override
    public boolean jump(int obstacleHeight) {
        if (maxHeight < obstacleHeight) {
            return false;
        }
        return true;

    }

    @Override
    public boolean run(int obstacleLength) {
        if (maxLength < obstacleLength) {
            return false;
        }
        return true;

    }
}
