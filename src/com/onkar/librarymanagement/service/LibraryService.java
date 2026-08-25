package com.onkar.librarymanagement.service;
import com.onkar.librarymanagement.model.User;
import com.onkar.librarymanagement.model.Book;
import com.onkar.librarymanagement.model.BorrowRecord;
import com.onkar.librarymanagement.exception.BookNotFoundException;
import com.onkar.librarymanagement.exception.UserNotFoundException;
import com.onkar.librarymanagement.exception.BookNotAvailableException;
import com.onkar.librarymanagement.exception.BookAlreadyIssuedException;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<BorrowRecord> borrowRecords = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayAllBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }
    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        throw new BookNotFoundException(
                "Book with ID " + id + " not found"
        );
    }
    public boolean removeBook(int id) {

        Book book = findBookById(id);

        if (!book.isAvailable()) {
            throw new BookAlreadyIssuedException(
                    "Book with ID " + id + " is currently issued and cannot be removed"
            );
        }

        books.remove(book);
        return true;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void displayAllUsers() {
        for (User user : users) {
            user.showRole();
        }
    }

    public User findUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        throw new UserNotFoundException(
                "User with ID " + id + " not found"
        );
    }
    public boolean issueBook(int bookId, int userId) {

        Book book = findBookById(bookId);
        User user = findUserById(userId);

        if (!book.isAvailable()) {
            throw new BookNotAvailableException(
                    "Book with ID " + bookId + " is already issued"
            );
        }

        BorrowRecord record = new BorrowRecord(
                borrowRecords.size() + 1,
                user,
                book,
                java.time.LocalDate.now(),
                java.time.LocalDate.now().plusDays(14),
                null
        );

        borrowRecords.add(record);

        book.markAsIssued();

        return true;
    }
    public boolean returnBook(int bookId) {

        Book book = findBookById(bookId);

        for (BorrowRecord record : borrowRecords) {

            if (record.getBook().getId() == bookId
                    && !record.isReturned()) {

                record.markAsReturned();
                book.markAsAvailable();

                return true;
            }
        }

        return false;
    }
}
