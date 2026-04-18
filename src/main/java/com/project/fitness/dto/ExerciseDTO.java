package com.project.fitness.dto;

public class ExerciseDTO {

    private Long id;
    private String name;
    private Integer sets;
    private Integer reps;

    public ExerciseDTO(Long id, String name, Integer sets, Integer reps) {
        this.id = id;
        this.name = name;
        this.sets = sets;
        this.reps = reps;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getSets() { return sets; }
    public Integer getReps() { return reps; }
}