package com.example.bugtracker.services;

import com.example.bugtracker.models.User;
import com.example.bugtracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    public UserRepository repository;

    // create new user
    public User saveUser(User user) throws SQLIntegrityConstraintViolationException {
        return repository.save(user);
    }

    // get users
    public User getUserById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    // update user

}
