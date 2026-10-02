package com.akshat.bakchodbrain.service;

import com.akshat.bakchodbrain.dto.*;
import com.akshat.bakchodbrain.model.Category;
import com.akshat.bakchodbrain.model.Question;
import com.akshat.bakchodbrain.model.QuizAttempt;
import com.akshat.bakchodbrain.repository.CategoryRepository;
import com.akshat.bakchodbrain.repository.QuestionRepository;
import com.akshat.bakchodbrain.repository.QuizAttemptRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizService {

    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;
    private final QuizAttemptRepository quizAttemptRepository;
    private final RoastMatrixService roastMatrixService;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryBySlug(String slug) {
        return categoryRepository.findBySlug(slug);
    }

    /**
     * Loads questions for a category, shuffles them, and strips correct answers for client safety.
     */
    public List<QuestionDto> getQuizQuestions(String categorySlug) {
        List<Question> questions = questionRepository.findByCategorySlug(categorySlug);
        if (questions.isEmpty()) {
            throw new RuntimeException("Category '" + categorySlug + "' me abhi koi questions nahi hain.");
        }

        // Shuffle questions and select a crisp round of 10 questions
        List<Question> shuffled = new ArrayList<>(questions);
        Collections.shuffle(shuffled);

        return shuffled.stream()
                .limit(10)
                .map(q -> QuestionDto.builder()
                .id(q.getId())
                .questionText(q.getQuestionText())
                .optionA(q.getOptionA())
                .optionB(q.getOptionB())
                .optionC(q.getOptionC())
                .optionD(q.getOptionD())
                .categorySlug(q.getCategory().getSlug())
                .categoryName(q.getCategory().getName())
                .difficulty(q.getDifficulty())
                .build()
        ).collect(Collectors.toList());
    }

    /**
     * Evaluates a single answer submission in real-time, computes streak and returns savage roast.
     */
    public AnswerResultResponse evaluateAnswer(AnswerSubmitRequest request) {
        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question ID " + request.getQuestionId() + " not found"));

        boolean isCorrect = question.getCorrectOption().trim().equalsIgnoreCase(request.getSelectedOption().trim());

        int currentStreak = request.getCurrentStreak() != null ? request.getCurrentStreak() : 0;
        int newStreak = isCorrect ? (currentStreak > 0 ? currentStreak + 1 : 1) : (currentStreak < 0 ? currentStreak - 1 : -1);

        double timeTaken = request.getTimeTakenSeconds() != null ? request.getTimeTakenSeconds() : 5.0;

        Map<String, String> roast = roastMatrixService.generateRoast(
                isCorrect,
                question.getCategory().getSlug(),
                timeTaken,
                newStreak,
                question.getCustomRoast(),
                question.getCustomPraise()
        );

        return AnswerResultResponse.builder()
                .isCorrect(isCorrect)
                .selectedOption(request.getSelectedOption().toUpperCase())
                .correctOption(question.getCorrectOption().toUpperCase())
                .explanation(question.getExplanation())
                .roastMessage(roast.get("roastMessage"))
                .roastTone(roast.get("tone"))
                .newStreak(newStreak)
                .audioEffect(roast.get("audio"))
                .build();
    }

    /**
     * Saves full quiz attempt and computes final scorecard.
     */
    @Transactional
    public QuizScorecardResponse finishQuiz(QuizFinishRequest request, HttpServletRequest httpRequest) {
        Category category = categoryRepository.findBySlug(request.getCategorySlug())
                .orElseThrow(() -> new RuntimeException("Category not found: " + request.getCategorySlug()));

        int total = request.getTotalQuestions() > 0 ? request.getTotalQuestions() : 1;
        double accuracy = ((double) request.getCorrectCount() / total) * 100.0;
        int score = (int) Math.round(accuracy);

        Map<String, String> evaluation = roastMatrixService.evaluateScorecard(score, accuracy, total);

        String ipHash = hashClientIp(httpRequest);

        QuizAttempt attempt = QuizAttempt.builder()
                .candidateName(request.getCandidateName().trim())
                .categorySlug(category.getSlug())
                .categoryName(category.getName())
                .totalQuestions(total)
                .correctCount(request.getCorrectCount())
                .wrongCount(request.getWrongCount())
                .score(score)
                .timeTakenSeconds(request.getTimeTakenSeconds())
                .accuracyRate(accuracy)
                .roastTitle(evaluation.get("title"))
                .savageSummary(evaluation.get("summary"))
                .ipHash(ipHash)
                .build();

        attempt = quizAttemptRepository.save(attempt);

        return QuizScorecardResponse.builder()
                .attemptId(attempt.getId())
                .candidateName(attempt.getCandidateName())
                .categorySlug(category.getSlug())
                .categoryName(category.getName())
                .totalQuestions(total)
                .correctCount(request.getCorrectCount())
                .wrongCount(request.getWrongCount())
                .score(score)
                .timeTakenSeconds(request.getTimeTakenSeconds())
                .accuracyRate(accuracy)
                .roastTitle(evaluation.get("title"))
                .savageSummary(evaluation.get("summary"))
                .rankBadge(evaluation.get("badge"))
                .memeVerdict(evaluation.get("verdict"))
                .completedAt(attempt.getCompletedAt())
                .build();
    }

    public QuizScorecardResponse getAttemptScorecard(Long attemptId) {
        QuizAttempt attempt = quizAttemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Quiz attempt not found: " + attemptId));

        Map<String, String> eval = roastMatrixService.evaluateScorecard(attempt.getScore(), attempt.getAccuracyRate(), attempt.getTotalQuestions());

        return QuizScorecardResponse.builder()
                .attemptId(attempt.getId())
                .candidateName(attempt.getCandidateName())
                .categorySlug(attempt.getCategorySlug())
                .categoryName(attempt.getCategoryName())
                .totalQuestions(attempt.getTotalQuestions())
                .correctCount(attempt.getCorrectCount())
                .wrongCount(attempt.getWrongCount())
                .score(attempt.getScore())
                .timeTakenSeconds(attempt.getTimeTakenSeconds())
                .accuracyRate(attempt.getAccuracyRate())
                .roastTitle(attempt.getRoastTitle())
                .savageSummary(attempt.getSavageSummary())
                .rankBadge(eval.get("badge"))
                .memeVerdict(eval.get("verdict"))
                .completedAt(attempt.getCompletedAt())
                .build();
    }

    private String hashClientIp(HttpServletRequest request) {
        if (request == null) return "unknown";
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isBlank()) {
            ip = request.getRemoteAddr();
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(ip.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                sb.append(String.format("%02x", bytes[i]));
            }
            return sb.toString();
        } catch (Exception e) {
            return "anon";
        }
    }
}
