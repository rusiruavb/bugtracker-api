package com.example.bugtracker.services;

import com.example.bugtracker.models.Issue;
import com.example.bugtracker.models.IssueUsers;
import com.example.bugtracker.repositories.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ListIterator;

@Service
public class IssueService {
    @Autowired
    public IssueRepository repository;

    // create new issue
    public Issue createIssue(Issue issue) {
        for (ListIterator<IssueUsers> iterator = issue.getUsers().listIterator(); iterator.hasNext();) {
            IssueUsers issueUsers = iterator.next();
            issue.getUsers().add(issueUsers);
        }

//        for (IssueUsers item: issue.getUsers()) {
//            issue.getUsers().add(item);
//        }
        System.out.println(issue);
        return repository.save(issue);
    }

}
