package com.onkar.librarymanagement;

import com.onkar.librarymanagement.exception.BookAlreadyIssuedException;
import com.onkar.librarymanagement.exception.BookNotAvailableException;
import com.onkar.librarymanagement.exception.BookNotFoundException;
import com.onkar.librarymanagement.model.Admin;
import com.onkar.librarymanagement.model.Book;
import com.onkar.librarymanagement.model.Student;
import com.onkar.librarymanagement.model.User;
import com.onkar.librarymanagement.service.LibraryService;

public class Main {

    public static void main(String[] args) {

        System.out.println("Library Management System");

        LibraryService libraryService = new LibraryService();

        // ---------------- BOOKS ----------------

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

        System.out.println("\nAll Books:");
        libraryService.displayAllBooks();

        // ---------------- FIND BOOK ----------------

        Book foundBook = libraryService.findBookById(1);

        System.out.println("\nBook found:");
        System.out.println(foundBook);

        // ---------------- REMOVE BOOK ----------------

        boolean removed = libraryService.removeBook(2);

        if (removed) {
            System.out.println("\nBook 2 removed successfully.");
        }

        System.out.println("\nBooks after removal:");
        libraryService.displayAllBooks();

        // ---------------- USERS ----------------

        Student student = new Student(1, "Onkar");
        Admin admin = new Admin(2, "Library Admin");

        libraryService.addUser(student);
        libraryService.addUser(admin);

        System.out.println("\nLibrary Users:");
        libraryService.displayAllUsers();

        // ---------------- FIND USER ----------------

        User foundUser = libraryService.findUserById(1);

        if (foundUser != null) {
            System.out.println("\nUser found: " + foundUser.getName());
            foundUser.showRole();
        } else {
            System.out.println("\nUser not found.");
        }

        // ---------------- ISSUE BOOK ----------------

        boolean issued = libraryService.issueBook(1, 1);

        if (issued) {
            System.out.println("\nBook issued successfully.");
        } else {
            System.out.println("\nUnable to issue book.");
        }

        System.out.println("\nBooks after issuing:");
        libraryService.displayAllBooks();

        // ---------------- BOOK NOT FOUND EXCEPTION ----------------

        try {
            libraryService.findBookById(99);
        } catch (BookNotFoundException e) {
            System.out.println("\nError: " + e.getMessage());
        }

        // ---------------- BOOK NOT AVAILABLE EXCEPTION ----------------

        try {
            libraryService.issueBook(1, 1);
        } catch (BookNotAvailableException e) {
            System.out.println("\nError: " + e.getMessage());
        }


        try {
            libraryService.removeBook(1);
        } catch (
                BookAlreadyIssuedException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }
}