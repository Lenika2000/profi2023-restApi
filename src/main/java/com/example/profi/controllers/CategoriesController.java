package com.example.profi.controllers;

import com.example.profi.exceptions.RequestException;
import com.example.profi.model.ShortInfAboutCategory;
import com.example.profi.repo.CategoriesRepo;
import com.example.profi.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    private CategoryService categoryService;

    public CategoriesController(CategoriesRepo categoriesRepo) {
        this.categoryService = new CategoryService(categoriesRepo);
    }

    @PostMapping
    private ResponseEntity<ShortInfAboutCategory> addPromotion(@RequestBody ShortInfAboutCategory category) throws RequestException {
        if (category.getName() == null) {
            throw new RequestException("Поле name обязательно для заполнения", 400);
        }
        return categoryService.createCategory(category);
    }

    @GetMapping
    private ResponseEntity<?> getAllCategories() throws RequestException {
        return categoryService.getAllCategories();
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<?> getCategory(@PathVariable Long id) throws RequestException {
        return categoryService.getCategory(id);
    }

    @PutMapping(value = "/{id}")
    private ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody ShortInfAboutCategory category) throws RequestException {
        return categoryService.updateCategory(category, id);
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<?> deleteCategory(@PathVariable Long id) throws RequestException {
        return categoryService.deleteCategory(id);
    }
}
