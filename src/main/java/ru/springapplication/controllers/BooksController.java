package ru.springapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.springapplication.dao.BooksDAO;
import ru.springapplication.dao.PersonDAO;
import ru.springapplication.models.Book;
import ru.springapplication.models.Person;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private BooksDAO booksDAO;
    private PersonDAO personDAO;

    @Autowired
    public BooksController(BooksDAO booksDAO, PersonDAO personDAO) {
        this.booksDAO = booksDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
         model.addAttribute("books", booksDAO.index());
         return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult ){

       if(bindingResult.hasErrors()){
           return "books/new";
       }
        booksDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") int id_books){
        model.addAttribute("book", booksDAO.show(id_books));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, @PathVariable("id") int id_books ){

        if(bindingResult.hasErrors()) return "books/edit";
        booksDAO.update(book, id_books);

        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id, @ModelAttribute("person")Person person){
        model.addAttribute("book", booksDAO.show(id));

        Optional<Person> bookOwner = booksDAO.getBookOwner(id);

        if(booksDAO.getBookOwner(id).isPresent()){

            model.addAttribute("owner",bookOwner.get());

        } else model.addAttribute("people", personDAO.index());

        return "books/show";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id_book){
        booksDAO.release(id_book);
        return "redirect:/books/" + id_book;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id_book, @ModelAttribute("person") Person person){
        booksDAO.assign(id_book,person.getId_person());
        return "redirect:/books/" + id_book;
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        booksDAO.delete(id);
        return "redirect:/books";
    }
}
