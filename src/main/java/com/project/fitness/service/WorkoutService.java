package com.project.fitness.service;

import com.project.fitness.dto.WorkoutDTO;
import com.project.fitness.model.User;
import com.project.fitness.model.Workout;
import com.project.fitness.repository.UserRepository;
import com.project.fitness.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    public WorkoutService(WorkoutRepository workoutRepository, UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
    }

    public WorkoutDTO createWorkout(Workout workout, String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        workout.setUser(user);

        Workout savedWorkout = workoutRepository.save(workout);

        return new WorkoutDTO(
                savedWorkout.getId(),
                savedWorkout.getTitle(),
                savedWorkout.getWorkoutDate(),
                savedWorkout.getDurationInMinutes()
        );
    }

    public List<WorkoutDTO> getMyWorkouts(String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return workoutRepository.findByUser(user)
                .stream()
                .map(w -> new WorkoutDTO(
                        w.getId(),
                        w.getTitle(),
                        w.getWorkoutDate(),
                        w.getDurationInMinutes()
                ))
                .collect(Collectors.toList());
    }

    public WorkoutDTO updateWorkout(Long id, Workout updatedWorkout, String email) {

        Workout existing = workoutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        if (!existing.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        existing.setTitle(updatedWorkout.getTitle());
        existing.setWorkoutDate(updatedWorkout.getWorkoutDate());
        existing.setDurationInMinutes(updatedWorkout.getDurationInMinutes());

        Workout saved = workoutRepository.save(existing);

        return new WorkoutDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getWorkoutDate(),
                saved.getDurationInMinutes()
        );
    }
}