package com.project.fitness.controller;

import com.project.fitness.dto.WorkoutDTO;
import com.project.fitness.model.Workout;
import com.project.fitness.service.WorkoutService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    public WorkoutDTO createWorkout(@RequestBody Workout workout, HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return workoutService.createWorkout(workout, email);
    }

    @GetMapping("/me")
    public List<WorkoutDTO> getMyWorkouts(HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return workoutService.getMyWorkouts(email);
    }

    @PutMapping("/{id}")
    public WorkoutDTO updateWorkout(@PathVariable Long id,
                                    @RequestBody Workout workout,
                                    HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return workoutService.updateWorkout(id, workout, email);
    }
}