package com.example.bugtracker.repositories;

import com.example.bugtracker.models.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
}
