package com.project.fitness.controller;

import com.project.fitness.ai.AIRecommendationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/ai")
public class AIController {

    private final AIRecommendationService aiService;

    public AIController(AIRecommendationService aiService) {
        this.aiService = aiService;
    }

    // 🔥 GET AI PLAN BASED ON USER GOAL
    @GetMapping("/recommendation")
    public String getRecommendation(HttpServletRequest request) throws IOException {

        // 🔥 get email from JWT filter
        String email = (String) request.getAttribute("email");

        if (email == null) {
            throw new RuntimeException("Unauthorized: No email found in request");
        }

        return aiService.generatePlan(email);
    }
}