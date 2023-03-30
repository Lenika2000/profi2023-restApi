package com.example.profi.controllers;

import com.example.profi.exceptions.RequestException;
import com.example.profi.model.ShortInfAboutArticle;
import com.example.profi.repo.ArticleRepo;
import com.example.profi.repo.CategoriesRepo;
import com.example.profi.services.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticlesController {
    private ArticleService articleService;

    public ArticlesController(CategoriesRepo categoriesRepo, ArticleRepo articleRepo) {
        this.articleService = new ArticleService(categoriesRepo, articleRepo);
    }

    @PostMapping
    private ResponseEntity<ShortInfAboutArticle> addArticle(@RequestBody ShortInfAboutArticle article) throws RequestException {
        if (article.getName() == null) {
            throw new RequestException("Поле name обязательно для заполнения", 400);
        }
        return articleService.createArticle(article);
    }

    @GetMapping
    private ResponseEntity<?> getAllArticles() throws RequestException {
        return articleService.getAllArticles();
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<?> getArticle(@PathVariable Long id) throws RequestException {
        return articleService.getArticle(id);
    }

    @PutMapping(value = "/{id}")
    private ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody ShortInfAboutArticle article) throws RequestException {
        return articleService.updateArticle(article, id);
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<?> deleteArticle(@PathVariable Long id) throws RequestException {
        return articleService.deleteArticle(id);
    }
}
