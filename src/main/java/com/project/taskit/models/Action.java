package com.project.taskit.models;

import jakarta.persistence.*;

@Entity
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    String task;


}
