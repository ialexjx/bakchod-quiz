package com.akshat.bakchodbrain.repository;

import com.akshat.bakchodbrain.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategoryId(Long categoryId);

    @Query("SELECT q FROM Question q WHERE q.category.slug = :slug")
    List<Question> findByCategorySlug(@Param("slug") String slug);

    long countByCategoryId(Long categoryId);

    boolean existsByQuestionText(String questionText);
}
