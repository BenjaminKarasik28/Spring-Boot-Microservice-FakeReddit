package com.example.userapi.controller;

import com.example.userapi.exceptionhandling.EmailSignupException;
import com.example.userapi.exceptionhandling.ErrorResponse;
import com.example.userapi.exceptionhandling.ExistingUserSignupException;
import com.example.userapi.exceptionhandling.IncorrectLoginException;
import com.example.userapi.model.JwtResponse;
import com.example.userapi.model.User;
import com.example.userapi.service.UserService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "User login", notes = "Provide email and password to login", response = ResponseEntity.class)
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody User user) {
        return ResponseEntity.ok(new JwtResponse(userService.userLogin(user)));
    }

    @ApiOperation(value = "User signup", notes = "Provide email, username and password to sign up", response = ResponseEntity.class)
    @PostMapping("/signup")
    public ResponseEntity<?> userSignup(@RequestBody User newUser) {
        return ResponseEntity.ok(new JwtResponse(userService.userSignup(newUser)));
    }

    @ApiOperation(value = "Delete user", notes = "Provide username to delete")
    @DeleteMapping("/{username}")
    public Long deleteUserByUsername(@PathVariable String username) {
        return userService.deleteUserByUsername(username);
    }

    @ApiOperation(value = "User update", notes = "Provide username to update", response = User.class)
    @PutMapping("/{username}")
    public User updateUser(@PathVariable String username, @RequestBody User user) {
        return userService.updateUser(username, user);
    }


    @GetMapping("/post/{username}")
    public String getEmailFromUsername(@PathVariable String username){
        return userService.getEmailByUsername(username);
    }

    @ExceptionHandler({IncorrectLoginException.class, EmailSignupException.class, ExistingUserSignupException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception err){
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), err.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
