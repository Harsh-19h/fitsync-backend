package com.project.fitness.repository;

import com.project.fitness.model.Workout;
import com.project.fitness.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUser (User user);
}
