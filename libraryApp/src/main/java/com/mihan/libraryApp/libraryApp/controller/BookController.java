package com.mihan.libraryApp.libraryApp.controller;


import com.mihan.libraryApp.libraryApp.model.Book;
import com.mihan.libraryApp.libraryApp.model.User;
import com.mihan.libraryApp.libraryApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return  bookService.addBook(book);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteBook(Long id){
        bookService.deleteBook(id);
        return "book deleted successfully!";
    }
}
