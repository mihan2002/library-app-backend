package com.mihan.libraryApp.libraryApp.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "borrowing_records")
public class BorrowingRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date borrowedDate;

    @Temporal(TemporalType.DATE)
    private Date returnedDate;

    // Default Constructor (Required by JPA)
    public BorrowingRecords() {}

    // Constructor to Set Borrow Date
    public BorrowingRecords(User user, Book book) {
        this.user = user;
        this.book = book;
        this.borrowedDate = new Date(); // Automatically sets borrow date
    }

    // Getters
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    // Setter for Returned Date
    public void setReturnedDate() {
        this.returnedDate = new Date();
    }
}
