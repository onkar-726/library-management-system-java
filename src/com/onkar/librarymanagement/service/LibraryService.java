package com.onkar.librarymanagement.service;

import com.onkar.librarymanagement.exception.BookAlreadyIssuedException;
import com.onkar.librarymanagement.exception.BookNotAvailableException;
import com.onkar.librarymanagement.exception.BookNotFoundException;
import com.onkar.librarymanagement.exception.BookNotIssuedException;
import com.onkar.librarymanagement.exception.DuplicateBookException;
import com.onkar.librarymanagement.exception.DuplicateUserException;
import com.onkar.librarymanagement.exception.StudentBorrowLimitException;
import com.onkar.librarymanagement.exception.UserNotFoundException;

import com.onkar.librarymanagement.model.Book;
import com.onkar.librarymanagement.model.BorrowRecord;
import com.onkar.librarymanagement.model.Student;
import com.onkar.librarymanagement.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {

    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<BorrowRecord> borrowRecords = new ArrayList<>();

    // ==================== BOOK METHODS ====================

    public void addBook(Book book) {

        if (findBookWithoutException(book.getId()) != null) {
            throw new DuplicateBookException(
                    "Book with ID " + book.getId() + " already exists"
            );
        }

        books.add(book);
    }

    public void displayAvailableBooks() {

        boolean found = false;

        for (Book book : books) {

            if (book.isAvailable()) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books are currently available.");
        }
    }

    public Book findBookById(int id) {

        Book book = findBookWithoutException(id);

        if (book == null) {
            throw new BookNotFoundException(
                    "Book with ID " + id + " not found"
            );
        }

        return book;
    }

    private Book findBookWithoutException(int id) {

        for (Book book : books) {

            if (book.getId() == id) {
                return book;
            }
        }

        return null;
    }

    public boolean removeBook(int id) {

        Book book = findBookById(id);

        if (!book.isAvailable()) {
            throw new BookAlreadyIssuedException(
                    "Book with ID " + id +
                            " is currently issued and cannot be removed"
            );
        }

        books.remove(book);

        return true;
    }

    // ==================== USER METHODS ====================

    public void addUser(User user) {

        if (findUserWithoutException(user.getId()) != null) {
            throw new DuplicateUserException(
                    "User with ID " + user.getId() +
                            " already exists"
            );
        }

        users.add(user);
    }

    public void displayAllUsers() {

        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        for (User user : users) {

            System.out.println(
                    "ID: " + user.getId() +
                            " | Name: " + user.getName() +
                            " | Role: " +
                            user.getClass().getSimpleName()
            );
        }
    }

    public User findUserById(int id) {

        User user = findUserWithoutException(id);

        if (user == null) {
            throw new UserNotFoundException(
                    "User with ID " + id + " not found"
            );
        }

        return user;
    }

    private User findUserWithoutException(int id) {

        for (User user : users) {

            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    // ==================== ISSUE BOOK ====================

    public boolean issueBook(int bookId, int userId) {

        Book book = findBookById(bookId);
        User user = findUserById(userId);

        if (!book.isAvailable()) {
            throw new BookNotAvailableException(
                    "Book with ID " + bookId +
                            " is already issued"
            );
        }

        if (user instanceof Student) {

            Student student = (Student) user;

            if (student.getBorrowedBooks() >= 3) {

                throw new StudentBorrowLimitException(
                        "Student " + student.getName() +
                                " has already borrowed 3 books"
                );
            }
        }

        BorrowRecord record = new BorrowRecord(
                borrowRecords.size() + 1,
                user,
                book,
                LocalDate.now(),
                LocalDate.now().plusDays(14),
                null
        );

        borrowRecords.add(record);

        book.markAsIssued();

        if (user instanceof Student) {

            Student student = (Student) user;
            student.borrowBook();
        }

        return true;
    }

    // ==================== RETURN BOOK ====================

    public boolean returnBook(int bookId) {

        Book book = findBookById(bookId);

        for (BorrowRecord record : borrowRecords) {

            if (record.getBook().getId() == bookId
                    && !record.isReturned()) {

                record.markAsReturned();

                book.markAsAvailable();

                User user = record.getUser();

                if (user instanceof Student) {

                    Student student = (Student) user;
                    student.returnBook();
                }

                return true;
            }
        }

        throw new BookNotIssuedException(
                "Book with ID " + bookId +
                        " is not currently issued"
        );
    }

    // ==================== BORROWING HISTORY ====================

    public void displayBorrowRecords() {

        if (borrowRecords.isEmpty()) {
            System.out.println("No borrowing records found.");
            return;
        }

        for (BorrowRecord record : borrowRecords) {
            System.out.println(record);
        }
    }

    public List<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }

    // ==================== FINE ====================

    public long getFineForBook(int bookId) {

        for (BorrowRecord record : borrowRecords) {

            if (record.getBook().getId() == bookId) {
                return record.calculateFine();
            }
        }

        throw new BookNotFoundException(
                "No borrowing record found for Book ID " + bookId
        );
    }
}