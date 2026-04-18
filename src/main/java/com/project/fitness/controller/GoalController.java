package com.project.fitness.controller;

import com.project.fitness.dto.GoalDTO;
import com.project.fitness.model.Goal;
import com.project.fitness.service.GoalService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    public GoalDTO createGoal(@RequestBody Goal goal,
                              HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return goalService.createGoal(goal, email);
    }

    @GetMapping("/me")
    public List<GoalDTO> getMyGoals(HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return goalService.getMyGoals(email);
    }

    @PutMapping("/{id}/complete")
    public GoalDTO completeGoal(@PathVariable Long id,
                                HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return goalService.completeGoal(id, email);
    }
}