package com.project.fitness.dto;

import java.time.LocalDate;

public class GoalDTO {

    private Long id;
    private Double targetWeight;
    private LocalDate startDate;
    private LocalDate targetDate;
    private String goalType;

    public GoalDTO(Long id, Double targetWeight,
                   LocalDate startDate, LocalDate targetDate,
                   String goalType) {

        this.id = id;
        this.targetWeight = targetWeight;
        this.startDate = startDate;
        this.targetDate = targetDate;
        this.goalType = goalType;
    }

    public Long getId() { return id; }
    public Double getTargetWeight() { return targetWeight; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getTargetDate() { return targetDate; }
    public String getGoalType() { return goalType; }
}