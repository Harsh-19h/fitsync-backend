package com.project.fitness.service;

import com.project.fitness.dto.GoalDTO;
import com.project.fitness.model.Goal;
import com.project.fitness.model.User;
import com.project.fitness.repository.GoalRepository;
import com.project.fitness.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalService {

    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    public GoalService(GoalRepository goalRepository, UserRepository userRepository) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
    }


    public GoalDTO createGoal(Goal goal, String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Goal existingActiveGoal = goalRepository.findByUserAndStatus(user,"ACTIVE");
        if(existingActiveGoal != null){
            throw new RuntimeException("User already has an active goal");
        }
        goal.setUser(user);
        goal.setStatus("ACTIVE");

        Goal saved = goalRepository.save(goal);

        return new GoalDTO(
                saved.getId(),
                saved.getTargetWeight(),
                saved.getStartDate(),
                saved.getTargetDate(),
                saved.getGoalType()
        );
    }

    public List<GoalDTO> getMyGoals(String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return goalRepository.findByUser(user)
                .stream()
                .map(g -> new GoalDTO(
                        g.getId(),
                        g.getTargetWeight(),
                        g.getStartDate(),
                        g.getTargetDate(),
                        g.getGoalType()
                ))
                .collect(Collectors.toList());
    }

    public GoalDTO completeGoal(Long id, String email) {

        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        if (!goal.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        goal.setStatus("COMPLETED");

        Goal saved = goalRepository.save(goal);

        return new GoalDTO(
                saved.getId(),
                saved.getTargetWeight(),
                saved.getStartDate(),
                saved.getTargetDate(),
                saved.getGoalType()
        );
    }
}