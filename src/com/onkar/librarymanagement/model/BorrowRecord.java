package com.onkar.librarymanagement.model;

import java.time.LocalDate;

public class BorrowRecord {

    private int recordId;
    private User user;
    private Book book;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public BorrowRecord(int recordId, User user, Book book,
                        LocalDate issueDate, LocalDate dueDate,
                        LocalDate returnDate) {

        this.recordId = recordId;
        this.user = user;
        this.book = book;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public void markAsReturned() {
        this.returnDate = LocalDate.now();
    }
}