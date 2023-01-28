package com.project.taskit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Task> tasks;

    public Category() {
    }

    public Category(String type) {
        this.type = type;
    }

}
