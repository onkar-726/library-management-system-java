package com.onkar.librarymanagement.model;

public class Student extends User {
    private int borrowedBooks = 0;
    public Student(int id, String name) {
        super(id, name);
    }
    public int getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook() {
        borrowedBooks++;
    }

    public void returnBook() {
        if (borrowedBooks > 0) {
            borrowedBooks--;
        }
    }
    @Override
    public void showRole() {
        System.out.println("Student");
    }
}