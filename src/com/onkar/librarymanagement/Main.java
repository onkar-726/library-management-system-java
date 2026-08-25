package com.onkar.librarymanagement;

import com.onkar.librarymanagement.model.*;
import com.onkar.librarymanagement.service.LibraryService;
public class Main {

    public static void main(String[] args) {

        System.out.println("Library Management System");

        LibraryService libraryService = new LibraryService();

        Book book1 = new Book(
                1,
                "Clean Code",
                "Robert C. Martin",
                "Programming"
        );

        Book book2 = new Book(
                2,
                "Effective Java",
                "Joshua Bloch",
                "Programming"
        );

        libraryService.addBook(book1);
        libraryService.addBook(book2);
//
//        libraryService.displayAllBooks();
        libraryService.removeBook(2);
        Book foundBook = libraryService.findBookById(2);

        if (foundBook != null) {
            System.out.println("Book found: " + foundBook);
        }
        Student student = new Student(1, "Onkar");
        libraryService.addUser(student);
        Admin admin = new Admin(2, "Library Admin");
        libraryService.addUser(admin);
        System.out.println("\nLibrary Users:");
        libraryService.displayAllUsers();
        User foundUser = libraryService.findUserById(1);

        if (foundUser != null) {
            System.out.println("\nUser found: " + foundUser.getName());
            foundUser.showRole();
        } else {
            System.out.println("\nUser not found.");
        }
        boolean issued = libraryService.issueBook(1, 1);

        if (issued) {
            System.out.println("\nBook issued successfully.");
        } else {
            System.out.println("\nUnable to issue book.");
        }
        System.out.println("\nBooks after issuing:");
        libraryService.displayAllBooks();
    }
}