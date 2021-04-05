package com.example.bugtracker.controllers;

import com.example.bugtracker.models.Issue;
import com.example.bugtracker.services.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IssueController {
    @Autowired
    private IssueService service;

    @PostMapping("/issue/create")
    public Issue saveIssue(Issue issue) {
        System.out.println(issue.getUsers());
        return service.createIssue(issue);
    }
}
