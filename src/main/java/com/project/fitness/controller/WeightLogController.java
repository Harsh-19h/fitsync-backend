package com.project.fitness.controller;

import com.project.fitness.dto.WeightLogDTO;
import com.project.fitness.model.WeightLog;
import com.project.fitness.service.WeightLogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weightlogs")
public class WeightLogController {

    private final WeightLogService weightLogService;

    public WeightLogController(WeightLogService weightLogService) {
        this.weightLogService = weightLogService;
    }

    @PostMapping
    public WeightLogDTO createWeightLog(@RequestBody WeightLog weightLog,
                                        HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return weightLogService.createWeightLog(weightLog, email);
    }

    @GetMapping("/me")
    public List<WeightLogDTO> getMyWeightLogs(HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return weightLogService.getMyWeightLogs(email);
    }

    @PutMapping("/{id}")
    public WeightLogDTO updateWeightLog(@PathVariable Long id,
                                        @RequestBody WeightLog weightLog,
                                        HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return weightLogService.updateWeightLog(id, weightLog, email);
    }
}