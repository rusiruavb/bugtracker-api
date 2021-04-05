package com.example.bugtracker.services;

import com.example.bugtracker.exception.ApiRequestException;
import com.example.bugtracker.models.User;
import com.example.bugtracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    public UserRepository repository;

    // create new user
    public User saveUser(User user) {
        try {
            return repository.save(user);
        } catch (DataIntegrityViolationException ex) {
            System.out.println("Error");
            throw  new ApiRequestException("Cannnot creat user");
        }
    }

    // get users
    public User getUserById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    // update user
    public User updateUser(User user) {
        User existingUser = repository.findById(user.getId()).orElse(null);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setPassword(user.getPassword());
        return repository.save(existingUser);
    }

    // delete user
    public String deleteUser(int id) {
        repository.deleteById(id);
        return "User removed !!";
    }
}
