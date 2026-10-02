package com.akshat.bakchodbrain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerResultResponse {

    @JsonProperty("isCorrect")
    private boolean isCorrect;

    @JsonProperty("correct")
    public boolean getCorrect() {
        return isCorrect;
    }

    private String selectedOption;
    private String correctOption;
    private String explanation;
    private String roastMessage;
    private String roastTone; // "SAVAGE", "CONDESCENDING", "ARROGANT_PRAISE", "STREAK_INSULT"
    private int newStreak;
    private String audioEffect; // "ding", "buzz", "fail_gong", "victory_fanfare"
}
