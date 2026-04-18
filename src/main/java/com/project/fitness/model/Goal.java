package com.project.fitness.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double targetWeight;

    private LocalDate startDate;

    private LocalDate targetDate;

    private String goalType;

    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Long getId() { return id; }
    public Double getTargetWeight() { return targetWeight; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getTargetDate() { return targetDate; }
    public String getGoalType() { return goalType; }
    public User getUser() { return user; }
    public String getStatus(){
        return status;
    }


    public void setId(Long id) { this.id = id; }
    public void setTargetWeight(Double targetWeight) { this.targetWeight = targetWeight; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setTargetDate(LocalDate targetDate) { this.targetDate = targetDate; }
    public void setGoalType(String goalType) { this.goalType = goalType; }
    public void setUser(User user) { this.user = user; }
    public void setStatus (String status){this.status = status;}
}