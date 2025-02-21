package com.mihan.libraryApp.libraryApp.repository;

import com.mihan.libraryApp.libraryApp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findById(Long id);

}
