package com.project.fitness.repository;

import com.project.fitness.model.Workout;
import com.project.fitness.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUser (User user);

    Page<Workout> findByUser(User user, Pageable pageable);
}
