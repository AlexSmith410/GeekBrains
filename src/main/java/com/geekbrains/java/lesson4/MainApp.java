package com.geekbrains.java.lesson4;

import java.io.IOException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int columns = sc.nextInt();
        String[][] array = new String[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                array[i][j] = sc.next();
            }
        }
        int result;
        try {
            result = sum2DArray(array);
            System.out.println(result);
        } catch (MyArraySizeException e) {
            System.err.println("Длина массива должна быть 4х4!");
        } catch (MyArrayDataException e) {
            System.err.printf("Неверный формат числа в ячейке [%d], [%d]", e.getI() + 1, e.getJ() + 1);
        }

    }

    public static int sum2DArray(String[][] arr) {
        if (arr.length != 4 || arr[0].length != 4) {
            throw new MyArraySizeException();
        }
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }
}
