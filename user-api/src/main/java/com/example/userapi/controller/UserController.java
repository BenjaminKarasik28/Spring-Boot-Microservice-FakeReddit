package com.example.userapi.controller;

import com.example.userapi.exceptionhandling.ErrorResponse;
import com.example.userapi.exceptionhandling.IncorrectLoginException;
import com.example.userapi.model.JwtResponse;
import com.example.userapi.model.User;
import com.example.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String test(){
        return "Hello World!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody User user) {
        return ResponseEntity.ok(new JwtResponse(userService.userLogin(user)));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> userSignup(@RequestBody User newUser) {
        return ResponseEntity.ok(new JwtResponse(userService.userSignup(newUser)));
    }

    @DeleteMapping("/{username}")
    public Long deleteUserByUsername(@PathVariable String username) {
        return userService.deleteUserByUsername(username);
    }

    @PutMapping("/{username}")
    public User updateUser(@PathVariable String username, @RequestBody User user) {
        return userService.updateUser(username, user);
    }

    @GetMapping("/post/{username}")
    public String getEmailFromUsername(@PathVariable String username){
        return userService.getEmailByUsername(username);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(IncorrectLoginException err){
        ErrorResponse error = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), err.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}
