package ru.springapplication.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id_person")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_person;


    @NotEmpty(message = "Name should not be empty" )
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "fio")
    private String fio;

    @Column(name = "yearofbirth")
    private int yearofbirth;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person(int id_person,String fio, int yearofbirth) {
        this.id_person = id_person;
        this.fio = fio;
        this.yearofbirth = yearofbirth;
    }

    public Person(){

    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getYearofbirth() {
        return yearofbirth;
    }

    public void setYearofbirth(int yearofbirth) {
        this.yearofbirth = yearofbirth;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
