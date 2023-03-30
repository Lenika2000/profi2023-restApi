package com.example.profi.repo;

import com.example.profi.model.Article;
import com.example.profi.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepo extends CrudRepository<Article, Long> {
    List<Article> findAll();
}
