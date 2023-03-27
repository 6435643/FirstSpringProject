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
public class BooksDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BooksDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Books", new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO Books( name, author, year)VALUES (?,?,?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM books WHERE id_books=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void update(Book book, int id_book){
        jdbcTemplate.update("UPDATE books SET name=?, author=?, year=? WHERE id_books=?", book.getName(),book.getAuthor(),book.getYear(), id_book);
    }

    public Optional<Person> getBookOwner(int id){
        return jdbcTemplate.query("SELECT person.* from Books JOIN Person on books.id_person = person.id_person "
                + "WHERE books.id_books = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void release(int id_books){
        jdbcTemplate.update("UPDATE Books SET id_person=null WHERE id_books=?", id_books);
    }

    public void assign(int id_book, int id_person){
        jdbcTemplate.update("UPDATE Books SET id_person=? WHERE id_books=?", id_person, id_book);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Books WHERE id_books = ?", id);
    }
}
