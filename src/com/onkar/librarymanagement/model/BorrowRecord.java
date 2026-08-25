package com.onkar.librarymanagement.model;

import java.time.LocalDate;

public class BorrowRecord {

    private int recordId;
    private User user;
    private Book book;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    // Cleaned constructor assigning fields correctly
    public BorrowRecord(int recordId, User user, Book book, LocalDate issueDate, LocalDate dueDate, LocalDate returnDate) {
        this.recordId = recordId;
        this.user = user;
        this.book = book;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public int getRecordId() {
        return recordId;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returnDate != null;
    }
}
