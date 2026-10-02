package com.akshat.bakchodbrain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "quiz_attempts",
    indexes = {
        @Index(name = "idx_attempt_score", columnList = "score"),
        @Index(name = "idx_attempt_created", columnList = "completedAt")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String candidateName;

    @Column(nullable = false, length = 100)
    private String categorySlug;

    @Column(nullable = false, length = 100)
    private String categoryName;

    @Column(nullable = false)
    private Integer totalQuestions;

    @Column(nullable = false)
    private Integer correctCount;

    @Column(nullable = false)
    private Integer wrongCount;

    @Column(nullable = false)
    private Integer score; // e.g. 80 out of 100

    @Column(nullable = false)
    private Integer timeTakenSeconds;

    @Column(nullable = false)
    private Double accuracyRate; // percentage

    @Column(length = 150)
    private String roastTitle; // e.g. "Certified Gawar", "TCS Bench Legend", "Absolute Einstein"

    @Column(columnDefinition = "TEXT")
    private String savageSummary;

    @Column(length = 64)
    private String ipHash;

    @Column(nullable = false)
    private LocalDateTime completedAt;

    @PrePersist
    protected void onCreate() {
        if (this.completedAt == null) {
            this.completedAt = LocalDateTime.now();
        }
    }
}
