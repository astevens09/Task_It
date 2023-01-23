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

    @Temporal(value = TemporalType.DATE)
    @Column
    private Date dateCreated;

    @Temporal(value = TemporalType.DATE)
    @Column
    private Date scheduledDate;

    @Column
    private String title;

    @Column
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<Category> categories;

}
