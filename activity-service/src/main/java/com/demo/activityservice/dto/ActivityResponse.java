package com.demo.activityservice.dto;

import com.demo.activityservice.model.ActivityType;

import java.time.LocalDateTime;
import java.util.Map;

public record ActivityResponse(
    String id,
    String userId,
    ActivityType type,
    Integer duration,
    Integer caloriesBurned,
    LocalDateTime startTime,
    Map<String, Object> additionalMetrics,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
