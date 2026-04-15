package com.project.fitness.dto;

import java.time.LocalDate;

public class WorkoutDTO {
    private Long id;
    private String title;
    private LocalDate workoutDate;
    private Integer durationInMinutes;

    public WorkoutDTO(Long id, String title, LocalDate workoutDate, Integer durationInMinutes){
        this.id = id;
        this.title = title;
        this.workoutDate = workoutDate;
        this.durationInMinutes = durationInMinutes;
    }

    public Long getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }

    public LocalDate getWorkoutDate(){
        return workoutDate;
    }

    public Integer getDurationInMinutes(){
        return durationInMinutes;
    }
}
