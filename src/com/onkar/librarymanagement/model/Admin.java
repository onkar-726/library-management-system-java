package com.onkar.librarymanagement.model;

public class Admin extends User {

    public Admin(int id, String name) {
        super(id, name);
    }

    @Override
    public void showRole() {
        System.out.println("Admin");
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                '}';
    }
}