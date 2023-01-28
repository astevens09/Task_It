package com.project.taskit.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String action;

    @Column
    private String dateCreated;

    @Column
    private String scheduledDate;

    @Column
    private String title;

    @Column
    private String completed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Task() {
    }

    public Task(String action, String dateCreated, String scheduledDate, String completed, User user, Category category) {
        this.action = action;
        this.dateCreated = dateCreated;
        this.scheduledDate = scheduledDate;

        this.completed = completed;
        this.user = user;
        this.category = category;
    }
}
