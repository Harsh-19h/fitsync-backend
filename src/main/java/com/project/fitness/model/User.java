package com.project.fitness.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Workout> workouts;

    @OneToMany(mappedBy = "user")
    private List<Meal> meals;

    @OneToMany(mappedBy = "user")
    private List<Goal> goals;

    @OneToMany(mappedBy = "user")
    private List<WeightLog> weightLogs;

}