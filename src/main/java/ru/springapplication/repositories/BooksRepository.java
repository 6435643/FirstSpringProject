package ru.springapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.springapplication.models.Book;

@Repository
public interface   BooksRepository extends JpaRepository<Book,Integer> {
}
