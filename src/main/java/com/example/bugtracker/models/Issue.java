package com.example.bugtracker.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "issues")
public class Issue {
    @Id
    @GeneratedValue
    private int id;
    @CreationTimestamp
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();
    @UpdateTimestamp
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();
    @UpdateTimestamp
    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt = new Date();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<IssueUsers> users = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "created_user", referencedColumnName = "id")
    private User issueCreatedUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<IssueUsers> getUsers() {
        return users;
    }

    public void setUsers(List<IssueUsers> users) {
        this.users = users;
    }

    public User getIssueCreatedUser() {
        return issueCreatedUser;
    }

    public void setIssueCreatedUser(User issueCreatedUser) {
        this.issueCreatedUser = issueCreatedUser;
    }
}
