package com.project.fitness.service;

import com.project.fitness.dto.ExerciseDTO;
import com.project.fitness.model.Exercise;
import com.project.fitness.model.Workout;
import com.project.fitness.repository.ExerciseRepository;
import com.project.fitness.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;

    public ExerciseService(ExerciseRepository exerciseRepository,
                           WorkoutRepository workoutRepository) {
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
    }


    public ExerciseDTO addExercise(Long workoutId, Exercise exercise, String email) {

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));


        if (!workout.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        exercise.setWorkout(workout);

        Exercise saved = exerciseRepository.save(exercise);

        return new ExerciseDTO(
                saved.getId(),
                saved.getName(),
                saved.getSets(),
                saved.getReps()
        );
    }


    public List<ExerciseDTO> getExercisesByWorkout(Long workoutId, String email) {

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));


        if (!workout.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        return exerciseRepository.findByWorkout(workout)
                .stream()
                .map(e -> new ExerciseDTO(
                        e.getId(),
                        e.getName(),
                        e.getSets(),
                        e.getReps()
                ))
                .collect(Collectors.toList());
    }
}