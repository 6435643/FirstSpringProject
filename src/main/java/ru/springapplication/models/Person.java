package ru.springapplication.models;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

    private int id_person;

    @NotEmpty(message = "Name should not be empty" )
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String fio;

    private int yearofbirth;

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
}
