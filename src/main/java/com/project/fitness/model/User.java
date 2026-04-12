package com.project.fitness.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Workout> workouts;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Meal> meals;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Goal> goals;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<WeightLog> weightLogs;

}