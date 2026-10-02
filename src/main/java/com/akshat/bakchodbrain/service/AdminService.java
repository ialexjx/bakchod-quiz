package com.akshat.bakchodbrain.service;

import com.akshat.bakchodbrain.dto.AdminOverviewResponse;
import com.akshat.bakchodbrain.dto.QuestionCreateRequest;
import com.akshat.bakchodbrain.model.Category;
import com.akshat.bakchodbrain.model.Question;
import com.akshat.bakchodbrain.model.QuizAttempt;
import com.akshat.bakchodbrain.repository.CategoryRepository;
import com.akshat.bakchodbrain.repository.QuestionRepository;
import com.akshat.bakchodbrain.repository.QuizAttemptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {

    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;
    private final QuizAttemptRepository quizAttemptRepository;

    @Value("${bakchod.admin.key:bakchodadmin69}")
    private String adminSecretKey;

    public boolean isValidAdminKey(String key) {
        return adminSecretKey != null && adminSecretKey.equals(key);
    }

    public AdminOverviewResponse getOverview() {
        long attempts = quizAttemptRepository.count();
        Double avg = quizAttemptRepository.calculateAverageScore();
        double avgScore = (avg != null) ? Math.round(avg * 10.0) / 10.0 : 0.0;
        long totalQuestions = questionRepository.count();
        long totalCategories = categoryRepository.count();
        long highScorers = quizAttemptRepository.countHighScorers() != null ? quizAttemptRepository.countHighScorers() : 0L;
        long roastees = quizAttemptRepository.countRoastees() != null ? quizAttemptRepository.countRoastees() : 0L;

        List<QuizAttempt> topLeaderboard = quizAttemptRepository.findTop10ByOrderByScoreDescTimeTakenSecondsAsc();

        return AdminOverviewResponse.builder()
                .totalAttempts(attempts)
                .averageScore(avgScore)
                .totalQuestions(totalQuestions)
                .totalCategories(totalCategories)
                .totalHighScorers(highScorers)
                .totalRoastees(roastees)
                .topLeaderboard(topLeaderboard)
                .build();
    }

    public Page<QuizAttempt> getAttempts(Pageable pageable) {
        return quizAttemptRepository.findAllByOrderByCompletedAtDesc(pageable);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Transactional
    public Question createQuestion(QuestionCreateRequest req) {
        Category category = categoryRepository.findBySlug(req.getCategorySlug())
                .orElseThrow(() -> new RuntimeException("Category not found: " + req.getCategorySlug()));

        Question question = Question.builder()
                .category(category)
                .questionText(req.getQuestionText().trim())
                .optionA(req.getOptionA().trim())
                .optionB(req.getOptionB().trim())
                .optionC(req.getOptionC().trim())
                .optionD(req.getOptionD().trim())
                .correctOption(req.getCorrectOption().trim().toUpperCase())
                .explanation(req.getExplanation())
                .customRoast(req.getCustomRoast())
                .customPraise(req.getCustomPraise())
                .difficulty(req.getDifficulty() != null ? req.getDifficulty() : "MEDIUM")
                .build();

        return questionRepository.save(question);
    }

    @Transactional
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
