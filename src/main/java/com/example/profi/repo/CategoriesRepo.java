package com.example.profi.repo;

import com.example.profi.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepo extends CrudRepository<Category, Long> {
    List<Category> findAll();
}
