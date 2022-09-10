package com.example.demo.Blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class ArticleConfig {
    @Bean
    CommandLineRunner commandLineRunner(ArticleRepository repository) {
        return args -> {
          Article article1 = new Article("django or flask", "which one is better?");
          Article article2 = new Article("python or java", "which one is better?");

            repository.saveAll(List.of(article1, article2));
        };
    }
}
