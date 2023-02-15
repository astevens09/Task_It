package com.project.taskit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    String task;

    @Column
    String completed;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;


}
