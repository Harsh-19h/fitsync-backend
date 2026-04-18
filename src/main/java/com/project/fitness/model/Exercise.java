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

    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getSets() { return sets; }
    public Integer getReps() { return reps; }
    public Workout getWorkout() { return workout; }


    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSets(Integer sets) { this.sets = sets; }
    public void setReps(Integer reps) { this.reps = reps; }
    public void setWorkout(Workout workout) { this.workout = workout; }
}