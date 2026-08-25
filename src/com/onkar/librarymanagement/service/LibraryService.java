package com.onkar.librarymanagement.service;
import com.onkar.librarymanagement.model.User;
import com.onkar.librarymanagement.model.Book;
import com.onkar.librarymanagement.model.BorrowRecord;
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
        return null;
    }

    public boolean removeBook(int id) {
        Book book = findBookById(id);
        if (book != null) {
            books.remove(book);
            return true;
        }
        return false;
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

        return null;
    }
    public boolean issueBook(int bookId, int userId) {

        Book book = findBookById(bookId);
        User user = findUserById(userId);

        if (book == null || user == null) {
            return false;
        }

        if (!book.isAvailable()) {
            return false;
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

        if (book == null) {
            return false;
        }

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
