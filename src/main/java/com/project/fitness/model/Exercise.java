package com.project.fitness.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer sets;

    private Integer reps;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

}