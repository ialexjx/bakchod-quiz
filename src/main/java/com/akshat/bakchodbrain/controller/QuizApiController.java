package com.akshat.bakchodbrain.controller;

import com.akshat.bakchodbrain.dto.*;
import com.akshat.bakchodbrain.model.Category;
import com.akshat.bakchodbrain.model.Question;
import com.akshat.bakchodbrain.service.AdminService;
import com.akshat.bakchodbrain.service.QuizService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class QuizApiController {

    private final QuizService quizService;
    private final AdminService adminService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(quizService.getAllCategories());
    }

    @GetMapping("/quiz/{slug}/questions")
    public ResponseEntity<List<QuestionDto>> getQuestions(@PathVariable String slug) {
        return ResponseEntity.ok(quizService.getQuizQuestions(slug));
    }

    @PostMapping("/quiz/submit-answer")
    public ResponseEntity<AnswerResultResponse> submitAnswer(@Valid @RequestBody AnswerSubmitRequest request) {
        return ResponseEntity.ok(quizService.evaluateAnswer(request));
    }

    @PostMapping("/quiz/finish")
    public ResponseEntity<QuizScorecardResponse> finishQuiz(@Valid @RequestBody QuizFinishRequest request,
                                                           HttpServletRequest httpRequest) {
        return ResponseEntity.ok(quizService.finishQuiz(request, httpRequest));
    }

    @GetMapping("/quiz/scorecard/{id}")
    public ResponseEntity<QuizScorecardResponse> getScorecard(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getAttemptScorecard(id));
    }

    @GetMapping("/admin/overview")
    public ResponseEntity<?> getAdminOverview(@RequestHeader(value = "X-Admin-Key", required = false) String adminKey) {
        if (!adminService.isValidAdminKey(adminKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Aukat me raho! Invalid Admin Key."));
        }
        return ResponseEntity.ok(adminService.getOverview());
    }

    @PostMapping("/admin/questions")
    public ResponseEntity<?> createQuestion(@RequestHeader(value = "X-Admin-Key", required = false) String adminKey,
                                            @Valid @RequestBody QuestionCreateRequest request) {
        if (!adminService.isValidAdminKey(adminKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Bsdk admin key to dal pehle!"));
        }
        Question created = adminService.createQuestion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/admin/questions/{id}")
    public ResponseEntity<?> deleteQuestion(@RequestHeader(value = "X-Admin-Key", required = false) String adminKey,
                                            @PathVariable Long id) {
        if (!adminService.isValidAdminKey(adminKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Khatam, tata, bye bye!"));
        }
        adminService.deleteQuestion(id);
        return ResponseEntity.ok(Map.of("message", "Question id " + id + " has been roasted out of existence."));
    }
}
