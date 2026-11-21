package com.demo.activityservice.dto;

import com.demo.activityservice.model.ActivityType;

import java.time.LocalDateTime;
import java.util.Map;

public record ActivityRequest(
    String userId,
    ActivityType type,
    Integer duration,
    Integer caloriesBurned,
    LocalDateTime startTime,
    Map<String, Object> additionalMetrics
) {
}
