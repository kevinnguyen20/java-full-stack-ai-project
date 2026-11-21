package com.demo.activityservice.service;

import com.demo.activityservice.repository.ActivityRepository;
import com.demo.activityservice.dto.ActivityResponse;
import com.demo.activityservice.dto.ActivityRequest;
import com.demo.activityservice.model.Activity;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final Environment environment;
    private final MongoTemplate mongoTemplate;

    public ActivityService(ActivityRepository activityRepository, Environment environment, MongoTemplate mongoTemplate) {
        this.activityRepository = activityRepository;
        this.environment = environment;
        this.mongoTemplate = mongoTemplate;
    }

    public ActivityResponse trackActivity(ActivityRequest request){
        Activity activity = new Activity();
        activity.setUserId(request.userId());
        activity.setType(request.type());
        activity.setDuration(request.duration());
        activity.setCaloriesBurned(request.caloriesBurned());
        activity.setStartTime(request.startTime());
        activity.setAdditionalMetrics(request.additionalMetrics());

        Activity savedActivity = activityRepository.save(activity);

        return mapToResponse(savedActivity);
    }

    private ActivityResponse mapToResponse(Activity activity){
        return new ActivityResponse(
            activity.getId(),
            activity.getUserId(),
            activity.getType(),
            activity.getDuration(),
            activity.getCaloriesBurned(),
            activity.getStartTime(),
            activity.getAdditionalMetrics(),
            activity.getCreatedAt(),
            activity.getUpdatedAt()
        );
    }

    public List<ActivityResponse> getUserActivities(String userId) {
        List<Activity> activities = activityRepository.findByUserId(userId);
        return activities.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ActivityResponse getActivityById(String activityId) {
        return activityRepository.findById(activityId)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + activityId));
    }
}
