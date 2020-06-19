package com.geekbrains.java.lesson2;

import java.util.ArrayList;

public class Group {
    private String name;
    private ArrayList<Employee> employees = new ArrayList<>();
    private int employeeCount = 0;

    public Group() {
        name = "Unknown group";
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEmployee() {
        if (employeeCount < 10) {
            Employee employee = new Employee();
            employees.add(employee);
            employeeCount++;
        } else
            System.out.println("Group is full!");
    }

    public void addEmployee(String name, int age, String email, String position) {
        if (employeeCount < 10) {
            Employee employee = new Employee(name, age, email, position);
            employees.add(employee);
            employeeCount++;
        } else
            System.out.println("Group is full!");
    }

    public void removeEmployee(int index) {
        employees.remove(index);
        employeeCount--;
    }

    public void clearGroup() {
        employees.clear();
        employeeCount = 0;
    }

    public void printAll() {
        for (Employee employee : employees
        ) {
            employee.getInfo();
        }
    }

}
