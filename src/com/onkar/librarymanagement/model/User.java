package com.onkar.librarymanagement.model;

public abstract class User {

    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public abstract void showRole();
}