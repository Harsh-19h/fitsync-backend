package com.project.fitness.controller;

import com.project.fitness.dto.MealDTO;
import com.project.fitness.model.Meal;
import com.project.fitness.service.MealService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meals")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }


    @PostMapping
    public MealDTO createMeal(@RequestBody Meal meal,
                              HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return mealService.createMeal(meal, email);
    }

    @GetMapping("/me")
    public List<MealDTO> getMyMeals(HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return mealService.getMyMeals(email);
    }

    @PutMapping("/{id}")
    public MealDTO updateMeal(@PathVariable Long id,
                              @RequestBody Meal meal,
                              HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return mealService.updateMeal(id, meal, email);
    }
}