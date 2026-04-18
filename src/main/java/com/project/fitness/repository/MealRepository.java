package com.project.fitness.repository;

import com.project.fitness.model.Meal;
import com.project.fitness.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long>{
    List<Meal> findByUser(User user);

    Page<Meal> findByUser(User user, Pageable pageable);
}
