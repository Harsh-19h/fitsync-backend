package com.project.fitness.repository;

import com.project.fitness.model.WeightLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightLogRepository extends JpaRepository<WeightLog, Long>{

}
