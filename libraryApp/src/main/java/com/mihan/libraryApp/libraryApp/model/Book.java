package com.mihan.libraryApp.libraryApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  String bookName;

    @Column(nullable = false)
    private  String author;


    @Column(nullable = false)
    private  String category;

    @Column(nullable = false)
    private boolean available;


    public Book() {
        this.available = true;
    }

    public Book(String bookName, String author, String category) {
        this.bookName = bookName;
        this.author = author;
        this.category = category;
        this.available = true;
    }

    public Long getId() {
        return id;
    }



    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
