package ru.springapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.springapplication.models.Person;
import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer>{

    List<Person> findByFio(String fio);
}
