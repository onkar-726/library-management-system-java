package com.onkar.librarymanagement.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BorrowRecord {

    private int recordId;
    private User user;
    private Book book;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public BorrowRecord(
            int recordId,
            User user,
            Book book,
            LocalDate issueDate,
            LocalDate dueDate,
            LocalDate returnDate) {

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

    public boolean isReturned() {
        return returnDate != null;
    }

    public void markAsReturned() {
        this.returnDate = LocalDate.now();
    }

    public long calculateFine() {

        if (returnDate == null) {
            return 0;
        }

        if (returnDate.isAfter(dueDate)) {

            long lateDays = ChronoUnit.DAYS.between(
                    dueDate,
                    returnDate
            );

            return lateDays * 10;
        }

        return 0;
    }

    @Override
    public String toString() {

        String status;

        if (isReturned()) {
            status = "RETURNED";
        } else {
            status = "BORROWED";
        }

        return "BorrowRecord{" +
                "recordId=" + recordId +
                ", user=" + user.getName() +
                ", book=" + book.getTitle() +
                ", issueDate=" + issueDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", status=" + status +
                ", fine=₹" + calculateFine() +
                '}';
    }
}