package com.project.fitness.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "weight_logs")
public class WeightLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double weight;

    private LocalDate logDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}