package com.onkar.librarymanagement;
import com.onkar.librarymanagement.model.Admin;
import com.onkar.librarymanagement.model.Book;
import com.onkar.librarymanagement.model.Student;
import com.onkar.librarymanagement.model.User;

public class Main {
        public static void main(String[] args) {
            System.out.println("Library Management System");
//            Book b =new Book(0,"Valan","Pandit Londhe","Poem");
//            System.out.println(b);
//            System.out.println("Original title: " + b.getTitle());
//            b.setTitle("Clean Code - Updated Edition");
//            System.out.println("Updated title: " + b.getTitle());
            User user1 = new Student(1, "Onkar");
            User user2 = new Admin(2, "Admin");

            user1.showRole();
            user2.showRole();
        }
    }