package com.project.fitness.repository;

import com.project.fitness.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long>{

}
