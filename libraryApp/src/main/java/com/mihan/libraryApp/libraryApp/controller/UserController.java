package com.mihan.libraryApp.libraryApp.controller;


import com.mihan.libraryApp.libraryApp.dto.BorrowRequestDTO;
import com.mihan.libraryApp.libraryApp.dto.LoginRequestDTO;
import com.mihan.libraryApp.libraryApp.dto.ReturnBookDTO;
import com.mihan.libraryApp.libraryApp.model.BorrowingRecords;
import com.mihan.libraryApp.libraryApp.model.User;
import com.mihan.libraryApp.libraryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO loginRequest){
        Optional<User> user = userService.logInUser(loginRequest.getEmail(),loginRequest.getPassword());

        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

    }

    @PostMapping("/borrow_book")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowRequestDTO borrowRequestDTO){
        BorrowingRecords borrowingRecords = userService.borrowBook(borrowRequestDTO.getUserId(),borrowRequestDTO.getBookId());
        return  ResponseEntity.ok(borrowingRecords);

    }

    @PostMapping("/return_book")
    public ResponseEntity<?> returnBook(@RequestBody ReturnBookDTO returnBookDTO){
        BorrowingRecords borrowingRecords = userService.returnBook(returnBookDTO.getBorrowingRecordID());
        return  ResponseEntity.ok(borrowingRecords);

    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
        return "User deleted successfully!";
    }
}
