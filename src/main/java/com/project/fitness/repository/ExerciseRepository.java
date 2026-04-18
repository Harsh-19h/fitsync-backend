package com.project.fitness.repository;

import com.project.fitness.model.Exercise;
import com.project.fitness.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByWorkout(Workout workout);

}
