package ru.springapplication.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;
import ru.springapplication.models.Book;
import ru.springapplication.models.Person;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person (fio, yearofbirth) VALUES (?,?)",
                person.getFio(),person.getYearofbirth());
    }

    public Optional<Person> show(String fio){
        return jdbcTemplate.query("SELECT * FROM Person WHERE fio=?",new Object[] {fio}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id_person=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void update(int id, Person person){
        jdbcTemplate.update("UPDATE Person SET fio=?, yearofbirth=? WHERE id_person=?",
                person.getFio(),person.getYearofbirth(), id);
    }

    public List<Book> showBooks(int id_person) {
        return jdbcTemplate.query("SELECT * FROM Books WHERE id_person = ?", new Object[]{id_person},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void delete(int id_person){
        jdbcTemplate.update("DELETE FROM Person WHERE id_person=?", id_person);
    }
}
