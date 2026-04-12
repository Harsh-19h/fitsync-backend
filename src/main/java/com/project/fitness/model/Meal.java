package com.project.fitness.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "meals")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer calories;

    private LocalDate mealDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}