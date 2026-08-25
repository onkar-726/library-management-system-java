package com.onkar.librarymanagement;

import com.onkar.librarymanagement.exception.BookAlreadyIssuedException;
import com.onkar.librarymanagement.exception.BookNotAvailableException;
import com.onkar.librarymanagement.exception.BookNotFoundException;
import com.onkar.librarymanagement.exception.UserNotFoundException;
import com.onkar.librarymanagement.exception.DuplicateBookException;
import com.onkar.librarymanagement.model.Admin;
import com.onkar.librarymanagement.model.Book;
import com.onkar.librarymanagement.model.Student;
import com.onkar.librarymanagement.model.User;
import com.onkar.librarymanagement.service.LibraryService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LibraryService libraryService = new LibraryService();

        // Sample books
        libraryService.addBook(
                new Book(1, "Clean Code", "Robert C. Martin", "Programming")
        );

        libraryService.addBook(
                new Book(2, "Effective Java", "Joshua Bloch", "Programming")
        );

        // Sample users
        libraryService.addUser(
                new Student(1, "Onkar")
        );

        libraryService.addUser(
                new Admin(2, "Library Admin")
        );

        boolean running = true;

        while (running) {

            System.out.println("\n======================================");
            System.out.println("      LIBRARY MANAGEMENT SYSTEM");
            System.out.println("======================================");
            System.out.println("1. Display Available Books");
            System.out.println("2. Find Book");
            System.out.println("3. Add Book");
            System.out.println("4. Remove Book");
            System.out.println("5. Display Users");
            System.out.println("6. Find User");
            System.out.println("7. Issue Book");
            System.out.println("8. Return Book");
            System.out.println("0. Exit");
            System.out.println("======================================");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("\n--- Available Books ---");
                    libraryService.displayAvailableBooks();
                    break;

                case 2:
                    System.out.print("Enter Book ID: ");
                    int searchBookId = scanner.nextInt();

                    try {
                        Book book = libraryService.findBookById(searchBookId);
                        System.out.println("\nBook Found:");
                        System.out.println(book);
                    } catch (BookNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();

                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine();

                    Book newBook = new Book(
                            bookId,
                            title,
                            author,
                            category
                    );

                    try {
                        libraryService.addBook(newBook);
                        System.out.println("Book added successfully.");

                    } catch (DuplicateBookException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                case 4:
                    System.out.print("Enter Book ID to remove: ");
                    int removeBookId = scanner.nextInt();

                    try {
                        boolean removed = libraryService.removeBook(removeBookId);

                        if (removed) {
                            System.out.println("Book removed successfully.");
                        }
                    } catch (BookNotFoundException |
                             BookAlreadyIssuedException e) {

                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("\n--- Library Users ---");
                    libraryService.displayAllUsers();
                    break;

                case 6:
                    System.out.print("Enter User ID: ");
                    int searchUserId = scanner.nextInt();

                    try {
                        User user = libraryService.findUserById(searchUserId);

                        System.out.println("\nUser Found:");
                        System.out.println("Name: " + user.getName());
                        user.showRole();

                    } catch (UserNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 7:
                    System.out.print("Enter Book ID: ");
                    int issueBookId = scanner.nextInt();

                    System.out.print("Enter User ID: ");
                    int issueUserId = scanner.nextInt();

                    try {
                        libraryService.issueBook(
                                issueBookId,
                                issueUserId
                        );

                        System.out.println("Book issued successfully.");

                    } catch (BookNotFoundException |
                             UserNotFoundException |
                             BookNotAvailableException e) {

                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 8:
                    System.out.print("Enter Book ID to return: ");
                    int returnBookId = scanner.nextInt();

                    try {
                        boolean returned =
                                libraryService.returnBook(returnBookId);

                        if (returned) {
                            System.out.println(
                                    "Book returned successfully."
                            );
                        } else {
                            System.out.println(
                                    "Book is not currently issued."
                            );
                        }

                    } catch (BookNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 0:
                    running = false;
                    System.out.println(
                            "\nThank you for using the Library Management System!"
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }

        scanner.close();
    }
}