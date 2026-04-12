package com.project.fitness.repository;

import com.project.fitness.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long>{

}
