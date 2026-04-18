package com.project.fitness.repository;

import com.project.fitness.model.WeightLog;
import com.project.fitness.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WeightLogRepository extends JpaRepository<WeightLog, Long>{

    List<WeightLog> findByUser(User user);
    Page<WeightLog> findByUser(User user, Pageable pageable);
}
