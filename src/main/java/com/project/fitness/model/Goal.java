package com.project.fitness.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private Double targetValue;

    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}