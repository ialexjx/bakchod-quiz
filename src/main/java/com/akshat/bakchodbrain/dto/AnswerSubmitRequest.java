package com.akshat.bakchodbrain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerSubmitRequest {

    @NotNull(message = "Question ID is required")
    private Long questionId;

    @NotNull(message = "Selected option is required")
    private String selectedOption; // "A", "B", "C", "D"

    private Double timeTakenSeconds; // e.g. 1.2, 4.5, 14.8

    private Integer currentStreak; // Positive for correct streak, negative for wrong streak
}
