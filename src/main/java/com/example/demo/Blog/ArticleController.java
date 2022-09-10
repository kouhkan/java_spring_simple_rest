package com.example.demo.Blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/articles")
public class ArticleController {
    final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getArticles() {
        return articleService.getArticles();
    }

    @PostMapping
    public void createArticle(@RequestBody Article article) {
        articleService.createArticle(article);
    }

    @DeleteMapping(path = "{articleId}")
    public void deleteArticle(@PathVariable("articleId") Long articleId) {
        articleService.deleteArticle(articleId);
    }

    @PutMapping(path = "{articleId}")
    public void updateArticle(
            @PathVariable("articleId") Long articleId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content) {

        articleService.updateArticle(articleId, title, content);
    }
}
