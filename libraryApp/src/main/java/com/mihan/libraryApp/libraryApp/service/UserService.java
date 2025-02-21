package com.mihan.libraryApp.libraryApp.service;


import com.mihan.libraryApp.libraryApp.dto.LoginRequestDTO;
import com.mihan.libraryApp.libraryApp.exception.ResourceNotFoundException;
import com.mihan.libraryApp.libraryApp.exception.UserAlreadyExistsException;
import com.mihan.libraryApp.libraryApp.model.Book;
import com.mihan.libraryApp.libraryApp.model.BorrowingRecords;
import com.mihan.libraryApp.libraryApp.model.User;
import com.mihan.libraryApp.libraryApp.repository.BookRepository;
import com.mihan.libraryApp.libraryApp.repository.BorrowingRecordsRepository;
import com.mihan.libraryApp.libraryApp.repository.UserRepository;
import com.mihan.libraryApp.libraryApp.util.JwtUtil;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.Authenticator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BorrowingRecordsRepository borrowingRecordsRepository;

    private final BookRepository bookRepository;

    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, BookRepository bookRepository, BorrowingRecordsRepository borrowingRecordsRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.bookRepository = bookRepository;
        this.borrowingRecordsRepository = borrowingRecordsRepository;
        this.userRepository = userRepository;
    }

    public String authenticate(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

   }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists.");
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        return userRepository.save(user);

    }

    public Optional<User> logInUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    @Transactional
    public BorrowingRecords borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book with ID " + bookId + " not found"));


        if (!book.isAvailable()) {
            throw new IllegalStateException("Book with ID " + bookId + " is not available for borrowing.");
        }

        book.setAvailable(false);
        bookRepository.save(book);

        BorrowingRecords borrowingRecords = new BorrowingRecords(user, book);

        return borrowingRecordsRepository.save(borrowingRecords);
    }

    @Transactional
    public BorrowingRecords returnBook(Long borrowingRecordID) {
        BorrowingRecords borrowingRecords = borrowingRecordsRepository.findById(borrowingRecordID).orElseThrow(() -> new ResourceNotFoundException("Record with ID " + borrowingRecordID + " is not found"));

        if (borrowingRecords.getReturnedDate() != null) {
            throw new IllegalStateException("Book with ID " + borrowingRecords.getBook().getId() + " is already returned.");
        }
        borrowingRecords.setReturnedDate();

        return borrowingRecordsRepository.save(borrowingRecords);
    }


    @Transactional
    public void deleteUser(Long id) {
        borrowingRecordsRepository.deleteById(id);
        userRepository.deleteById(id);
    }
}
