package com.example.bugtracker.services;

import com.example.bugtracker.models.Issue;
import com.example.bugtracker.repositories.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueService {
    @Autowired
    public IssueRepository repository;

    // create new issue
    public Issue createIssue(Issue issue) {
        System.out.println(issue.getUsers());
        return repository.save(issue);
    }

}
