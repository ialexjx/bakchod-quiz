package com.akshat.bakchodbrain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizFinishRequest {

    @NotBlank(message = "Apna nickname ya naam bata bsdk")
    private String candidateName;

    @NotBlank(message = "Category slug is required")
    private String categorySlug;

    @NotNull
    private Integer totalQuestions;

    @NotNull
    private Integer correctCount;

    @NotNull
    private Integer wrongCount;

    @NotNull
    private Integer timeTakenSeconds;
}
