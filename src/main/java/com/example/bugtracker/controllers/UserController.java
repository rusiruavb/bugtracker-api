package com.example.bugtracker.controllers;

import com.example.bugtracker.models.User;
import com.example.bugtracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/user/create")
    public User createUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @GetMapping("/user/get")
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        for (User item: service.getUsers()) {
            User userItem = new User(item.getId(), item.getFirstName(), item.getLastName(),
                    item.getEmail(), item.getPhone(), item.getRole(), item.getUserName());
            userList.add(userItem);
        }
        return userList;
    }

    @GetMapping("/user/get/{id}")
    public User getUser(@PathVariable  int id) {
        return service.getUserById(id);
    }

    @PutMapping("/user/update")
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        return service.deleteUser(id);
    }
}
