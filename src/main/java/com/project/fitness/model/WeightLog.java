package com.project.fitness.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "weight_logs")
public class WeightLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double weight;

    private LocalDate logDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Long getId() {
        return id;
    }

    public Double getWeight() {
        return weight;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    public User getUser() {
        return user;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setLogDate(LocalDate logDate) {
        this.logDate = logDate;
    }

    public void setUser(User user) {
        this.user = user;
    }
}