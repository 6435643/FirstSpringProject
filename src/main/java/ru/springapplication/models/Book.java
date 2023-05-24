package ru.springapplication.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Books")
public class Book {

    @Id
    @Column(name = "id_books")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_books;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 1, max = 30, message = "Name should be between 1 and 30 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Author should not be empty")
    @Size(min = 1, max = 30, message = "Author should be between 1 and 30 characters")
    @Column(name = "author")
    private String author;

    @Column(name = "year")
    private int year;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Transient
    private boolean overdue;

    @ManyToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id_person")
    private Person owner;

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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Date getDateOfIssue() {
        return date;
    }

    public void setDateOfIssue(Date date) {
        this.date = date;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }
}
