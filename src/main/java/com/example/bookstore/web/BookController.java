package com.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.CategoryRepository;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
    @Autowired
    private BookRepository bookrepository;
    @Autowired
    private CategoryRepository categoryrepository;

    @RequestMapping(value = { "/", "/index" })
    public String bookList(Model model) {
        model.addAttribute("books", bookrepository.findAll());
        return "index";
    }

    @RequestMapping(value = "/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categorys", categoryrepository.findAll());
        return "addbook";
    }

    @RequestMapping(value = "/savebook", method = RequestMethod.POST)
    public String saveBook(Book book) {
        bookrepository.save(book);
        return "redirect:index";
    }

    @RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        bookrepository.deleteById(bookId);
        return "redirect:../index";
    }

    @RequestMapping(value = "/editbook/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        model.addAttribute("book", bookrepository.findById(bookId));
        return "editbook";
    }

}
