package com.onkar.librarymanagement.service;

import com.onkar.librarymanagement.model.Book;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private List<Book> books = new ArrayList<>();
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

}