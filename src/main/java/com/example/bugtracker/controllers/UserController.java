package com.example.bugtracker.controllers;

import com.example.bugtracker.models.User;
import com.example.bugtracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/createuser")
    public User createUser(@RequestBody User user) {
        try {
            return service.saveUser(user);
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Already Exists", ex);
        }
    }

    @GetMapping("/getusers")
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        for (User item: service.getUsers()) {
            User userItem = new User(item.getId(), item.getFirstName(), item.getLastName(),
                    item.getEmail(), item.getPhone(), item.getRole(), item.getUserName());
            userList.add(userItem);
        }
        return userList;
    }

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable  int id) {
        return service.getUserById(id);
    }


}
