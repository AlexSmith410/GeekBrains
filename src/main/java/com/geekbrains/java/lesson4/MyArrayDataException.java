package com.geekbrains.java.lesson4;

public class MyArrayDataException extends RuntimeException {
    private int i;
    private int j;

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public MyArrayDataException(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public MyArrayDataException() {
    }

}
