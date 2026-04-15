package com.project.fitness.service;

import com.project.fitness.dto.WeightLogDTO;
import com.project.fitness.model.User;
import com.project.fitness.model.WeightLog;
import com.project.fitness.repository.UserRepository;
import com.project.fitness.repository.WeightLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeightLogService {

    private final WeightLogRepository weightLogRepository;
    private final UserRepository userRepository;

    public WeightLogService(WeightLogRepository weightLogRepository,
                            UserRepository userRepository) {
        this.weightLogRepository = weightLogRepository;
        this.userRepository = userRepository;
    }


    public WeightLogDTO createWeightLog(WeightLog weightLog, String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        weightLog.setUser(user);

        WeightLog saved = weightLogRepository.save(weightLog);

        return new WeightLogDTO(
                saved.getId(),
                saved.getWeight(),
                saved.getLogDate()
        );
    }


    public List<WeightLogDTO> getMyWeightLogs(String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return weightLogRepository.findByUser(user)
                .stream()
                .map(w -> new WeightLogDTO(
                        w.getId(),
                        w.getWeight(),
                        w.getLogDate()
                ))
                .collect(Collectors.toList());
    }
}