package com.project.fitness.repository;

import com.project.fitness.model.Goal;
import com.project.fitness.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long>{
    List<Goal> findByUser(User user);
    Goal findByUserAndStatus(User user, String status);

}
