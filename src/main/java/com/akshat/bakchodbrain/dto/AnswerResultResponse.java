package com.akshat.bakchodbrain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerResultResponse {
    private boolean isCorrect;
    private String selectedOption;
    private String correctOption;
    private String explanation;
    private String roastMessage;
    private String roastTone; // "SAVAGE", "CONDESCENDING", "ARROGANT_PRAISE", "STREAK_INSULT"
    private int newStreak;
    private String audioEffect; // "ding", "buzz", "fail_gong", "victory_fanfare"
}
