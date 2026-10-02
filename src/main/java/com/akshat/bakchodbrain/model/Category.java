package com.akshat.bakchodbrain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
    name = "categories",
    indexes = {
        @Index(name = "idx_category_slug", columnList = "slug", unique = true)
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String slug;

    @Column(length = 50)
    private String icon;

    @Column(length = 255)
    private String tagline;

    @Column(length = 30)
    private String colorAccent; // e.g. "sky", "emerald", "purple", "rose", "amber"

    @Column(columnDefinition = "TEXT")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Question> questions = new ArrayList<>();

    public String getIconEmoji() {
        if (icon == null) return "🔥";
        return switch (icon.toLowerCase()) {
            case "terminal" -> "💻";
            case "globe" -> "🌍";
            case "film" -> "🎬";
            case "heart-crack" -> "💔";
            default -> "⚡";
        };
    }
}
