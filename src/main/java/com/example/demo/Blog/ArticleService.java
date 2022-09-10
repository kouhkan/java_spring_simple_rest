package com.example.demo.Blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getArticles () {
        return articleRepository.findAll();
    }

    public void createArticle(Article article) {
        Optional<Article> articleOptional = articleRepository.findArticleByTitle(article.getTitle());

        if (articleOptional.isPresent()) {
            try {
                throw new IllegalAccessException("choose another title");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        articleRepository.save(article);
    }

    public void deleteArticle(Long articleId) {
        boolean articleExists = articleRepository.existsById(articleId);

        if (!articleExists) {
            try {
                throw new IllegalAccessException("Article does not exist " + articleId);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        articleRepository.deleteById(articleId);
    }

    @Transactional
    public void updateArticle(Long articleId, String title, String content) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new IllegalArgumentException("article does not exist " + articleId)
        );

        if (title != null && title.length() > 0 && !Objects.equals(article.getTitle(), title)) {
            article.setTitle(title);
        }
        if (content != null && content.length() > 0 && !Objects.equals(article.getContent(), content)) {
            article.setContent(content);
        }
        article.setUpdatedAt(LocalDate.now());
    }
}
