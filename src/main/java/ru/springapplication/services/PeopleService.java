package ru.springapplication.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.springapplication.models.Book;
import ru.springapplication.models.Person;
import ru.springapplication.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)

public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService( PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    public List<Person> findAll() {
        return peopleRepository.findAll();
    }


    public Optional<Person> show(int id_person){

        Optional<Person> person = peopleRepository.findById(id_person);

        return person;
    }

    public Optional<Person> show(String fio){
        List<Person> people = peopleRepository.findByFio(fio);
        return people.stream().findAny();
    }

    public List<Book> showBooks(int id_person){
        Person person = peopleRepository.findById(id_person).orElse(null);
        Hibernate.initialize(person.getBooks());
        return person.getBooks();
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id_person, Person updatedPerson){

        updatedPerson.setId_person(id_person);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int person_id){

        Person person = peopleRepository.findById(person_id).orElse(null);
        Hibernate.initialize(person.getBooks());

        if ( person.getBooks() != null){

            person.getBooks().forEach(i -> i.setOwner(null));
        }
        peopleRepository.deleteById(person_id);

    }

}
