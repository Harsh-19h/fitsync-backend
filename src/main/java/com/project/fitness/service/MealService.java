package com.project.fitness.service;

import com.project.fitness.dto.MealDTO;
import com.project.fitness.model.Meal;
import com.project.fitness.model.User;
import com.project.fitness.repository.MealRepository;
import com.project.fitness.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class MealService {

    private final MealRepository mealRepository;
    private final UserRepository userRepository;

    public MealService(MealRepository mealRepository, UserRepository userRepository) {
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
    }

    public MealDTO createMeal(Meal meal, String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        meal.setUser(user);

        Meal saved = mealRepository.save(meal);

        return new MealDTO(
                saved.getId(),
                saved.getMealName(),
                saved.getCalories(),
                saved.getProtein(),
                saved.getCarbs(),
                saved.getFats(),
                saved.getMealType(),
                saved.getMealDate()
        );
    }

    public List<MealDTO> getMyMeals(String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return mealRepository.findByUser(user)
                .stream()
                .map(m -> new MealDTO(
                        m.getId(),
                        m.getMealName(),
                        m.getCalories(),
                        m.getProtein(),
                        m.getCarbs(),
                        m.getFats(),
                        m.getMealType(),
                        m.getMealDate()
                ))
                .collect(Collectors.toList());
    }

    public MealDTO updateMeal(Long id, Meal updatedMeal, String email) {

        Meal existing = mealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meal not found"));

        if (!existing.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        existing.setMealName(updatedMeal.getMealName());
        existing.setCalories(updatedMeal.getCalories());
        existing.setProtein(updatedMeal.getProtein());
        existing.setCarbs(updatedMeal.getCarbs());
        existing.setFats(updatedMeal.getFats());
        existing.setMealType(updatedMeal.getMealType());
        existing.setMealDate(updatedMeal.getMealDate());

        Meal saved = mealRepository.save(existing);

        return new MealDTO(
                saved.getId(),
                saved.getMealName(),
                saved.getCalories(),
                saved.getProtein(),
                saved.getCarbs(),
                saved.getFats(),
                saved.getMealType(),
                saved.getMealDate()
        );
    }

    public List<MealDTO> getMyMealsPaginated(String email, int page, int size) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Pageable pageable = PageRequest.of(page, size);

        return mealRepository.findByUser(user, pageable)
                .stream()
                .map(m -> new MealDTO(
                        m.getId(),
                        m.getMealName(),
                        m.getCalories(),
                        m.getProtein(),
                        m.getCarbs(),
                        m.getFats(),
                        m.getMealType(),
                        m.getMealDate()
                ))
                .toList();
    }
}