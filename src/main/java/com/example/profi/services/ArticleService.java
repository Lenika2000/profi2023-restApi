package com.example.profi.services;

import com.example.profi.exceptions.RequestException;
import com.example.profi.model.*;
import com.example.profi.repo.ArticleRepo;
import com.example.profi.repo.CategoriesRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final CategoriesRepo categoriesRepo;
    private final ArticleRepo articleRepo;

    public ArticleService(CategoriesRepo categoriesRepo, ArticleRepo articleRepo) {
        this.categoriesRepo = categoriesRepo;
        this.articleRepo = articleRepo;
    }

    public ResponseEntity<ShortInfAboutArticle> createArticle(ShortInfAboutArticle newArticle) throws RequestException {
        boolean isFound = categoriesRepo.existsById(newArticle.getCategory_id());
        if (isFound) {
            Category category = categoriesRepo.findById(newArticle.getCategory_id()).get();
            Article createdArticle = articleRepo.save(new Article(0L, newArticle.getName(), newArticle.getDescription(), category));
            newArticle.setId(createdArticle.getId());
            return ResponseEntity.status(201).body(newArticle);
        } else {
            throw new RequestException("Категория с id " + newArticle.getCategory_id() + " не найдена", 404);
        }
    }

    public ResponseEntity<List<Article>> getAllArticles() throws RequestException {
        List<Article> articles = articleRepo.findAll();
        if (articles.isEmpty()) {
            throw new RequestException("Статьи не найдены", 404);
        } else {
            return ResponseEntity.status(200).body(articles);
        }
    }

    public ResponseEntity<ShortInfAboutArticle> getArticle(Long id) throws RequestException {
        boolean isFound = articleRepo.existsById(id);
        if (isFound) {
            Article article = articleRepo.findById(id).get();
            return ResponseEntity.status(200).body(new ShortInfAboutArticle(article.getId(),
                    article.getName(), article.getDescription(), article.getCategory().getId()));
        } else {
            throw new RequestException("Статья с id " + id + " не найдена", 404);
        }
    }

    public ResponseEntity<ShortInfAboutArticle> updateArticle(ShortInfAboutArticle shortInfAboutArticle, Long id) throws RequestException {
        boolean isFound = articleRepo.existsById(id);
        if (isFound) {
            Article article = articleRepo.findById(id).get();
            if (shortInfAboutArticle.getName() != null) article.setName(shortInfAboutArticle.getName());
            article.setDescription(shortInfAboutArticle.getDescription());
            if (shortInfAboutArticle.getCategory_id() != null) {
                boolean isCategoryFound = categoriesRepo.existsById(id);
                if (isCategoryFound) {
                    Category category = categoriesRepo.findById(id).get();
                    article.setCategory(category);
                } else {
                    throw new RequestException("Категория с id " + id + " не найдена", 404);
                }
            }
            Article updatedArticle = articleRepo.save(article);
            return ResponseEntity.status(200).body(new ShortInfAboutArticle(updatedArticle.getId(),
                    updatedArticle.getName(), updatedArticle.getDescription(), updatedArticle.getCategory().getId()));
        } else {
            throw new RequestException("Категория с id " + id + " не найдена", 404);
        }
    }

    public ResponseEntity<Long> deleteArticle(Long id) throws RequestException {
        boolean isFound = articleRepo.existsById(id);
        if (isFound) {
            articleRepo.deleteById(id);
            return ResponseEntity.status(200).body(id);
        } else {
            throw new RequestException("Статья с id " + id + " не найдена", 404);
        }
    }

}
