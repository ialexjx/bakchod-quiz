package com.akshat.bakchodbrain.dto;

import com.akshat.bakchodbrain.model.QuizAttempt;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminOverviewResponse {
    private long totalAttempts;
    private double averageScore;
    private long totalQuestions;
    private long totalCategories;
    private long totalRoastees;
    private long totalHighScorers;
    private List<QuizAttempt> topLeaderboard;
}
