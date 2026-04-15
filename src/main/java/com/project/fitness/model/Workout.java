package com.project.fitness.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "workouts")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate workoutDate;

    private Integer durationInMinutes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "workout")
    private List<Exercise> exercises;

    // 🔥 GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public User getUser() {
        return user;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWorkoutDate(LocalDate workoutDate) {
        this.workoutDate = workoutDate;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}