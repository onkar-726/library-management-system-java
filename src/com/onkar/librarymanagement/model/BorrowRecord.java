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
    public User getUser() {
        return user;
    }
    @Override
    public String toString() {
        return "BorrowRecord{" +
                "recordId=" + recordId +
                ", user=" + user.getName() +
                ", book=" + book.getTitle() +
                ", issueDate=" + issueDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", fine=₹" + calculateFine() +
                '}';
    }
    public long calculateFine() {

        if (returnDate == null) {
            return 0;
        }

        if (returnDate.isAfter(dueDate)) {
            long lateDays = java.time.temporal.ChronoUnit.DAYS.between(
                    dueDate,
                    returnDate
            );

            return lateDays * 10;
        }

        return 0;
    }
}