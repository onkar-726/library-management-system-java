package com.onkar.librarymanagement.exception;

public class StudentBorrowLimitException extends RuntimeException {
    public StudentBorrowLimitException(String message) {
        super(message);
    }
}
