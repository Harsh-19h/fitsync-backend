package com.project.fitness.controller;

import com.project.fitness.dto.ExerciseDTO;
import com.project.fitness.model.Exercise;
import com.project.fitness.service.ExerciseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }


    @PostMapping("/workout/{workoutId}")
    public ExerciseDTO addExercise(@PathVariable Long workoutId,
                                   @RequestBody Exercise exercise,
                                   HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return exerciseService.addExercise(workoutId, exercise, email);
    }

    @GetMapping("/workout/{workoutId}")
    public List<ExerciseDTO> getExercises(@PathVariable Long workoutId,
                                          HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return exerciseService.getExercisesByWorkout(workoutId, email);
    }
}