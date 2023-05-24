package ru.springapplication.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.springapplication.models.Book;
import ru.springapplication.models.Person;
import ru.springapplication.repositories.BooksRepository;
import ru.springapplication.repositories.PeopleRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll(){
        return booksRepository.findAll();
    }

    public Book findOne(int id){
        Book book = booksRepository.findById(id).orElse(null);
        return book;
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void updateBook(int id_book, Book updatedBook){
        updatedBook.setId_books(id_book);
        booksRepository.save(updatedBook);
    }

    public Person getBookOwner(int id_book){

        Optional<Book> optionalBook = booksRepository.findById(id_book);
        return optionalBook.orElse(null).getOwner();

    }

    @Transactional
    public void assign(int id_book, int id_person){

        Optional<Book> optionalBook = booksRepository.findById(id_book);
        Optional<Person> optionalPerson = peopleRepository.findById(id_person);

        Book book = optionalBook.orElse(null);
        Person owner = optionalPerson.orElse(null);


        book.setOwner(owner);

        if(owner.getBooks() == null){
            owner.setBooks(new ArrayList<>(Collections.singletonList(book)));
        }else {
            owner.getBooks().add(book);
        }
    }

    @Transactional
    public void release(int id_book){

        Book book = booksRepository.findById(id_book).orElse(null);
        Person owner = book.getOwner();


        owner.getBooks().remove(book);
        book.setOwner(null);
    }

    @Transactional
    public void delete(int id_book){

        Book book = booksRepository.findById(id_book).orElse(null);

        Person owner = book.getOwner();
        if(owner != null){
            owner.getBooks().remove(book);
        }
        booksRepository.deleteById(id_book);
    }

    public List<Book> paginate(Integer page, Integer perPage, String sort) {
        if(sort == null){
            return booksRepository.findAll(PageRequest.of(page,perPage)).getContent();
        } else {
            if(perPage > 0){
                if(sort.equals("true")){
                    return booksRepository.findAll(PageRequest.of(page,perPage,Sort.by("year"))).getContent();
                } else return booksRepository.findAll(PageRequest.of(page,perPage)).getContent();
            }
            if(sort.equals("true")){
                return sort();
            } else return findAll();
        }
    }

    public List<Book> sort(){
        return booksRepository.findAll(Sort.by("year"));
    }

    public List<Book> search(String name) {
        return booksRepository.findByNameStartingWith(name);
    }
}
