package com.project.fitness.repository;

import com.project.fitness.model.WeightLog;
import com.project.fitness.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeightLogRepository extends JpaRepository<WeightLog, Long>{

    List<WeightLog> findByUser(User user);
}
