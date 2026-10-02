package com.akshat.bakchodbrain.controller;

import com.akshat.bakchodbrain.model.Category;
import com.akshat.bakchodbrain.service.AdminService;
import com.akshat.bakchodbrain.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebQuizController {

    private final QuizService quizService;
    private final AdminService adminService;

    @GetMapping("/")
    public String index(Model model) {
        List<Category> categories = quizService.getAllCategories();
        model.addAttribute("categories", categories);
        return "index";
    }

    @GetMapping("/quiz/{slug}")
    public String quizArena(@PathVariable String slug,
                            @RequestParam(defaultValue = "Gumnam Bakchod") String name,
                            Model model) {
        Optional<Category> category = quizService.getCategoryBySlug(slug);
        if (category.isEmpty()) {
            return "redirect:/?error=category_not_found";
        }
        model.addAttribute("category", category.get());
        model.addAttribute("candidateName", name);
        return "quiz";
    }

    @GetMapping("/result/{id}")
    public String resultPage(@PathVariable Long id, Model model) {
        try {
            var scorecard = quizService.getAttemptScorecard(id);
            model.addAttribute("scorecard", scorecard);
            return "result";
        } catch (Exception e) {
            log.error("Failed to load scorecard for id: {}", id, e);
            return "redirect:/?error=result_not_found";
        }
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        var overview = adminService.getOverview();
        var recentAttempts = adminService.getAttempts(PageRequest.of(0, 50));
        var questions = adminService.getAllQuestions();
        var categories = quizService.getAllCategories();

        model.addAttribute("overview", overview);
        model.addAttribute("attempts", recentAttempts.getContent());
        model.addAttribute("questions", questions);
        model.addAttribute("categories", categories);
        return "admin";
    }

    @GetMapping("/health")
    @ResponseBody
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("BakchodBrain is alive and roasting! 🧠⚡");
    }
}
