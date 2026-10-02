package com.akshat.bakchodbrain;

import com.akshat.bakchodbrain.dto.AnswerSubmitRequest;
import com.akshat.bakchodbrain.dto.QuestionCreateRequest;
import com.akshat.bakchodbrain.dto.QuizFinishRequest;
import com.akshat.bakchodbrain.model.Question;
import com.akshat.bakchodbrain.repository.QuestionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BakchodBrainIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    @DisplayName("Health endpoint returns HTTP 200")
    void testHealthEndpoint() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("BakchodBrain is alive and roasting")));
    }

    @Test
    @DisplayName("Categories API returns seeded categories")
    void testGetCategories() throws Exception {
        mockMvc.perform(get("/api/v1/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(4))))
                .andExpect(jsonPath("$[0].slug", notNullValue()))
                .andExpect(jsonPath("$[0].name", notNullValue()));
    }

    @Test
    @DisplayName("Quiz Questions API hides correct answers for security")
    void testGetQuizQuestions() throws Exception {
        mockMvc.perform(get("/api/v1/quiz/tech-corporate/questions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].questionText", notNullValue()))
                .andExpect(jsonPath("$[0].optionA", notNullValue()))
                .andExpect(jsonPath("$[0].correctOption").doesNotExist());
    }

    @Test
    @DisplayName("Submit correct answer produces praise response")
    void testSubmitCorrectAnswer() throws Exception {
        Question q = questionRepository.findAll().get(0);

        AnswerSubmitRequest req = AnswerSubmitRequest.builder()
                .questionId(q.getId())
                .selectedOption(q.getCorrectOption())
                .timeTakenSeconds(2.5)
                .currentStreak(1)
                .build();

        mockMvc.perform(post("/api/v1/quiz/submit-answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correct", is(true)))
                .andExpect(jsonPath("$.correctOption", is(q.getCorrectOption())))
                .andExpect(jsonPath("$.roastMessage", notNullValue()))
                .andExpect(jsonPath("$.newStreak", is(2)));
    }

    @Test
    @DisplayName("Submit wrong answer produces savage roast")
    void testSubmitWrongAnswer() throws Exception {
        Question q = questionRepository.findAll().get(0);
        String wrongOpt = q.getCorrectOption().equalsIgnoreCase("A") ? "B" : "A";

        AnswerSubmitRequest req = AnswerSubmitRequest.builder()
                .questionId(q.getId())
                .selectedOption(wrongOpt)
                .timeTakenSeconds(14.0)
                .currentStreak(0)
                .build();

        mockMvc.perform(post("/api/v1/quiz/submit-answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correct", is(false)))
                .andExpect(jsonPath("$.correctOption", is(q.getCorrectOption())))
                .andExpect(jsonPath("$.roastMessage", notNullValue()))
                .andExpect(jsonPath("$.newStreak", is(-1)));
    }

    @Test
    @DisplayName("Finish quiz computes scorecard and persists attempt")
    void testFinishQuiz() throws Exception {
        QuizFinishRequest req = QuizFinishRequest.builder()
                .candidateName("Gaurav Gawar")
                .categorySlug("tech-corporate")
                .totalQuestions(5)
                .correctCount(1)
                .wrongCount(4)
                .timeTakenSeconds(35)
                .build();

        mockMvc.perform(post("/api/v1/quiz/finish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.attemptId", notNullValue()))
                .andExpect(jsonPath("$.score", is(20)))
                .andExpect(jsonPath("$.roastTitle", notNullValue()))
                .andExpect(jsonPath("$.memeVerdict", notNullValue()));
    }

    @Test
    @DisplayName("Admin Overview rejects invalid key and accepts valid key")
    void testAdminOverview() throws Exception {
        // Without header
        mockMvc.perform(get("/api/v1/admin/overview"))
                .andExpect(status().isUnauthorized());

        // With valid key
        mockMvc.perform(get("/api/v1/admin/overview")
                        .header("X-Admin-Key", "bakchodadmin69"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalQuestions", greaterThanOrEqualTo(10)))
                .andExpect(jsonPath("$.totalCategories", greaterThanOrEqualTo(4)));
    }

    @Test
    @DisplayName("Admin create and delete question works")
    void testAdminCreateAndDeleteQuestion() throws Exception {
        QuestionCreateRequest createReq = QuestionCreateRequest.builder()
                .categorySlug("tech-corporate")
                .questionText("Standup meeting me late aane ka best bahana kya hai?")
                .optionA("WiFi disconnect ho gaya tha")
                .optionB("Mummy ne dahi khila diya")
                .optionC("Laptop update pe chala gaya")
                .optionD("Alarm nahi baja")
                .correctOption("C")
                .explanation("OS update is universally respected as an act of God.")
                .customRoast("Tu to pakka roz late hi uthta hai")
                .customPraise("Pro corporate escape artist!")
                .difficulty("EASY")
                .build();

        String response = mockMvc.perform(post("/api/v1/admin/questions")
                        .header("X-Admin-Key", "bakchodadmin69")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createReq)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andReturn().getResponse().getContentAsString();

        Question created = objectMapper.readValue(response, Question.class);

        // Delete question
        mockMvc.perform(delete("/api/v1/admin/questions/" + created.getId())
                        .header("X-Admin-Key", "bakchodadmin69"))
                .andExpect(status().isOk());
    }
}
