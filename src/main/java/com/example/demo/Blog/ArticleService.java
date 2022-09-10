package com.example.demo.Blog;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ArticleService {
    public List<Article> getArticles () {
        return List.of(
                new Article(1L, "spring java is good or bad?", "some content here", LocalDate.now(), LocalDate.now()),
                new Article(2L, "django for rest", "some content here", LocalDate.now(), LocalDate.now())
        );
    }
}
