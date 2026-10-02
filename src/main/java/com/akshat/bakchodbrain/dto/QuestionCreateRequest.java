package com.akshat.bakchodbrain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionCreateRequest {

    @NotBlank(message = "Category slug is required")
    private String categorySlug;

    @NotBlank(message = "Question text cannot be blank")
    private String questionText;

    @NotBlank(message = "Option A cannot be blank")
    private String optionA;

    @NotBlank(message = "Option B cannot be blank")
    private String optionB;

    @NotBlank(message = "Option C cannot be blank")
    private String optionC;

    @NotBlank(message = "Option D cannot be blank")
    private String optionD;

    @NotBlank(message = "Correct option must be A, B, C, or D")
    private String correctOption; // "A", "B", "C", "D"

    private String explanation;
    private String customRoast;
    private String customPraise;
    private String difficulty;
}
