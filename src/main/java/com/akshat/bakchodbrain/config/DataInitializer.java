package com.akshat.bakchodbrain.config;

import com.akshat.bakchodbrain.model.Category;
import com.akshat.bakchodbrain.model.Question;
import com.akshat.bakchodbrain.repository.CategoryRepository;
import com.akshat.bakchodbrain.repository.QuestionRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void run(String... args) {
        log.info("BakchodBrain: Initializing arenas and expanding savage question bank...");

        // Ensure Categories exist
        Category tech = ensureCategory(
                "tech-corporate",
                "Broke Engineers & Corporate Majdoor",
                "terminal",
                "Jira tickets, Friday 5 PM prod deployments & LeetCode trauma",
                "sky",
                "For engineers who solve LeetCode Hard but can't center a div in CSS."
        );

        Category gk = ensureCategory(
                "desh-duniya",
                "Desh-Duniya & GK Bakchodi",
                "globe",
                "Mukherjee Nagar chai tapri geopolitics & UPSC trauma",
                "emerald",
                "Absurd real-world questions where confident people make catastrophic guesses."
        );

        Category bollywood = ensureCategory(
                "bollywood-memes",
                "Bollywood & Meme Culture",
                "film",
                "Hera Pheri, Gangs of Wasseypur, Panchayat & Iconic Dialogues",
                "purple",
                "If your brain is 90% memes and 10% oxygen, this is your arena."
        );

        Category dating = ensureCategory(
                "dating-delusion",
                "Dating, Simping & Heartbreak",
                "heart-crack",
                "Seen on WhatsApp, ghosting survivors & 'she is just a friend'",
                "rose",
                "For everyone whose heart is broken and whose Instagram screen time is 6 hours."
        );

        // Load questions from JSON
        loadQuestionsFromJson();

        log.info("BakchodBrain: Active question bank ready with {} questions loaded across 4 arenas!", questionRepository.count());
    }

    private Category ensureCategory(String slug, String name, String icon, String tagline, String color, String desc) {
        return categoryRepository.findBySlug(slug).orElseGet(() -> {
            Category c = Category.builder()
                    .slug(slug)
                    .name(name)
                    .icon(icon)
                    .tagline(tagline)
                    .colorAccent(color)
                    .description(desc)
                    .build();
            return categoryRepository.save(c);
        });
    }

    private void loadQuestionsFromJson() {
        try {
            ClassPathResource resource = new ClassPathResource("data/questions.json");
            if (!resource.exists()) {
                log.warn("questions.json not found in classpath. Skipping JSON bulk load.");
                return;
            }

            try (InputStream is = resource.getInputStream()) {
                List<Map<String, String>> rawQuestions = objectMapper.readValue(is, new TypeReference<>() {});
                int addedCount = 0;

                for (Map<String, String> qMap : rawQuestions) {
                    String slug = qMap.get("categorySlug");
                    String text = qMap.get("questionText");

                    // Avoid duplicate questions
                    if (questionRepository.existsByQuestionText(text)) {
                        continue;
                    }

                    var catOpt = categoryRepository.findBySlug(slug);
                    if (catOpt.isPresent()) {
                        Question q = Question.builder()
                                .category(catOpt.get())
                                .questionText(text.trim())
                                .optionA(qMap.get("optionA").trim())
                                .optionB(qMap.get("optionB").trim())
                                .optionC(qMap.get("optionC").trim())
                                .optionD(qMap.get("optionD").trim())
                                .correctOption(qMap.get("correctOption").trim().toUpperCase())
                                .explanation(qMap.get("explanation"))
                                .customRoast(qMap.get("customRoast"))
                                .customPraise(qMap.get("customPraise"))
                                .difficulty(qMap.getOrDefault("difficulty", "MEDIUM"))
                                .build();

                        questionRepository.save(q);
                        addedCount++;
                    }
                }

                if (addedCount > 0) {
                    log.info("BakchodBrain: Successfully seeded {} new savage questions from questions.json!", addedCount);
                }
            }
        } catch (Exception e) {
            log.error("Failed to load questions from JSON", e);
        }
    }
}
