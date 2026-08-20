package com.onkar.librarymanagement.model;

public class Student extends User {

    public Student(int id, String name) {
        super(id, name);
    }

    @Override
    public void showRole() {
        System.out.println("Student");
    }
}