package com.onkar.librarymanagement;

import com.onkar.librarymanagement.model.Book;
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
        Book foundBook = libraryService.findBookById(2);

        if (foundBook != null) {
            System.out.println("Book found: " + foundBook);
        }
    }
}