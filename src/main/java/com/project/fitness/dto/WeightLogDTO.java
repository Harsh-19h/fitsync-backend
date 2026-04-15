package com.project.fitness.dto;

import java.time.LocalDate;

public class WeightLogDTO {

    private Long id;
    private Double weight;
    private LocalDate logDate;

    public WeightLogDTO(Long id, Double weight, LocalDate logDate) {
        this.id = id;
        this.weight = weight;
        this.logDate = logDate;
    }

    public Long getId() {
        return id;
    }

    public Double getWeight() {
        return weight;
    }

    public LocalDate getLogDate() {
        return logDate;
    }
}