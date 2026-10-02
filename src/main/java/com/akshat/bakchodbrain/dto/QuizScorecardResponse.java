package com.akshat.bakchodbrain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizScorecardResponse {
    private Long attemptId;
    private String candidateName;
    private String categorySlug;
    private String categoryName;
    private int totalQuestions;
    private int correctCount;
    private int wrongCount;
    private int score;
    private int timeTakenSeconds;
    private double accuracyRate;
    private String roastTitle;
    private String savageSummary;
    private String rankBadge;
    private String memeVerdict;
    private LocalDateTime completedAt;
}
