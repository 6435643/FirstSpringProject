package ru.springapplication.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

    private int id_books;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 1, max = 30, message = "Name should be between 1 and 30 characters")
    private String name;

    @NotEmpty(message = "Author should not be empty")
    @Size(min = 1, max = 30, message = "Author should be between 1 and 30 characters")
    private String author;

    private int year;

    public Book(){

    }

    public Book(int id_books, String name, String author, int year) {
        this.id_books = id_books;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId_books() {
        return id_books;
    }

    public void setId_books(int id_books) {
        this.id_books = id_books;
    }

}
