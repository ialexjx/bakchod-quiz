package com.akshat.bakchodbrain.repository;

import com.akshat.bakchodbrain.model.QuizAttempt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {

    List<QuizAttempt> findTop10ByOrderByScoreDescTimeTakenSecondsAsc();

    Page<QuizAttempt> findAllByOrderByCompletedAtDesc(Pageable pageable);

    @Query("SELECT AVG(q.score) FROM QuizAttempt q")
    Double calculateAverageScore();

    @Query("SELECT COUNT(q) FROM QuizAttempt q WHERE q.score >= 80")
    Long countHighScorers();

    @Query("SELECT COUNT(q) FROM QuizAttempt q WHERE q.score < 40")
    Long countRoastees();
}
