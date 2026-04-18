package com.project.fitness.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;

@Entity
@Table(name = "meals")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mealName;

    private Integer calories;

    private Integer protein;

    private Integer carbs;

    private Integer fats;

    private String mealType;

    private LocalDate mealDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId(){
        return id;
    }

    public String getMealName(){
        return mealName;
    }

    public Integer getCalories(){
        return calories;
    }

    public Integer getProtein(){
        return protein;
    }

    public Integer getCarbs(){
        return carbs;
    }

    public Integer getFats(){
        return fats;
    }

    public String getMealType(){
        return mealType;
    }

    public LocalDate getMealDate(){
        return mealDate;
    }

    public User getUser(){
        return user;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    public void setCarbs(Integer carbs) {
        this.carbs = carbs;
    }

    public void setFats(Integer fats) {
        this.fats = fats;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public void setMealDate(LocalDate mealDate){
        this.mealDate = mealDate;
    }

    public void setUser(User user) {
        this.user = user;
    }
}