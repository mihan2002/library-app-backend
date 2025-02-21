package com.mihan.libraryApp.libraryApp.service;

import com.mihan.libraryApp.libraryApp.model.Book;
import com.mihan.libraryApp.libraryApp.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public Book addBook(Book book){
        return bookRepository.save(book);
    }


    @Transactional
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
}
