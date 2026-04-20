package com.project.fitness.ai;

import com.project.fitness.model.Goal;
import com.project.fitness.model.User;
import com.project.fitness.repository.GoalRepository;
import com.project.fitness.repository.UserRepository;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AIRecommendationService {

    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    @Value("${gemini.api.key}")
    private String apiKey;

    public AIRecommendationService(GoalRepository goalRepository,
                                   UserRepository userRepository) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
    }

    public String generatePlan(String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Goal goal = goalRepository.findByUserAndStatus(user, "ACTIVE");

        if (goal == null) {
            return "No active goal found. Please create a goal first.";
        }

        try {
            return callGemini(goal);   // 🔥 Try real AI
        } catch (Exception e) {
            return generateFallbackPlan(goal);  // 🔥 Fallback
        }
    }

    // 🔥 REAL AI CALL
    private String callGemini(Goal goal) throws Exception {

        String prompt = buildPrompt(goal);

        OkHttpClient client = new OkHttpClient();

        String jsonBody = """
        {
          "contents": [{
            "parts":[{"text": "%s"}]
          }]
        }
        """.formatted(prompt);

        Request request = new Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1/models/gemini-2.0-flash-lite:generateContent?key=" + apiKey)
                .post(RequestBody.create(jsonBody, MediaType.parse("application/json")))
                .build();

        Response response = client.newCall(request).execute();

        // ❌ If API fails → trigger fallback
        if (!response.isSuccessful()) {
            throw new RuntimeException("AI API failed");
        }

        return response.body().string();
    }

    // 🔥 FALLBACK LOGIC (VERY IMPORTANT)
    private String generateFallbackPlan(Goal goal) {

        String type = goal.getGoalType();

        if (type.equalsIgnoreCase("LOSE")) {
            return """
            🔥 Weight Loss Plan

            Workout:
            Day 1: Cardio + Abs
            Day 2: HIIT
            Day 3: Running
            Day 4: Strength + Core
            Day 5: Cardio
            Day 6: Full Body
            Day 7: Rest

            Diet:
            Breakfast: Oats + Fruits
            Lunch: Chicken + Rice
            Dinner: Salad + Soup
            """;
        }

        if (type.equalsIgnoreCase("GAIN")) {
            return """
            💪 Muscle Gain Plan

            Workout:
            Day 1: Chest + Triceps
            Day 2: Back + Biceps
            Day 3: Legs
            Day 4: Shoulders
            Day 5: Arms
            Day 6: Full Body
            Day 7: Rest

            Diet:
            Breakfast: Eggs + Milk
            Lunch: Chicken + Rice
            Dinner: Paneer + Roti
            """;
        }

        return """
        ⚖️ Maintenance Plan

        Workout:
        Mix of strength + cardio

        Diet:
        Balanced nutrition
        """;
    }

    private String buildPrompt(Goal goal) {
        return "Create a structured 7-day fitness plan.\n" +
                "Goal Type: " + goal.getGoalType() + "\n" +
                "Target Weight: " + goal.getTargetWeight() + "\n" +
                "Start Date: " + goal.getStartDate() + "\n" +
                "Target Date: " + goal.getTargetDate() + "\n\n" +
                "Provide:\n" +
                "1. Weekly Workout Plan (Day-wise)\n" +
                "2. Daily Diet Plan\n" +
                "3. Tips\n";
    }
}