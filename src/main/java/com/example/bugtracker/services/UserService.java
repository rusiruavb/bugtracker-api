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
            if (user.getUserName().length() > 8) {
                throw new ApiRequestException("Username must be only 8 characters");
            } else {
                return repository.save(user);
            }
        } catch (DataIntegrityViolationException ex) {
            System.out.println("Error" + ex);
            throw  new ApiRequestException("Username already exists");
        }
    }

    // get users
    public User getUserById(int id) {
        try {
            User user = repository.findById(id).orElse(null);
            if (user.equals(null)) {
                throw new ApiRequestException("There is no user for id " + id);
            } else {
                return user;
            }
        } catch (NullPointerException ex) {
            System.out.println("Error " + ex);
            throw new ApiRequestException("There is no user for id " + id);
        }
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    // update user
    public User updateUser(User user) {
        try {
            User existingUser = repository.findById(user.getId()).orElse(null);
            if (!existingUser.equals(null)) {
                existingUser.setFirstName(user.getFirstName());
                existingUser.setLastName(user.getLastName());
                existingUser.setEmail(user.getEmail());
                existingUser.setPhone(user.getPhone());
                existingUser.setPassword(user.getPassword());
                existingUser.setRole(user.getRole());
                return repository.save(existingUser);
            } else {
                throw new ApiRequestException("There is no user to update for id " + user.getId());
            }
        } catch (NullPointerException ex) {
            throw new ApiRequestException("There is no user to update for id " + user.getId());
        }
    }

    // delete user
    public String deleteUser(int id) {
        try {
            User user = repository.findById(id).orElse(null);
            if (!user.equals(null)) {
                repository.deleteById(id);
                return "User removed !!";
            } else {
                throw new ApiRequestException("There is no user to delete for id " + id);
            }
        } catch (NullPointerException ex) {
            throw new ApiRequestException("There is no user to delete for id " + id);
        }
    }
}
