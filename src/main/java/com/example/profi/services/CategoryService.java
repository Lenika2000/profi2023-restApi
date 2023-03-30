package com.example.profi.services;

import com.example.profi.exceptions.RequestException;
import com.example.profi.model.Category;
import com.example.profi.model.ShortInfAboutCategory;
import com.example.profi.repo.CategoriesRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoriesRepo categoriesRepo;

    public CategoryService(CategoriesRepo categoriesRepo) {
        this.categoriesRepo = categoriesRepo;
    }

    public ResponseEntity<ShortInfAboutCategory> createCategory(ShortInfAboutCategory category) {
        Category parent = null;
        if (category.getParent_id() != null) {
            parent = categoriesRepo.findById(category.getParent_id()).get();
        }
        Category createdCategory = categoriesRepo.save(new Category(0L, category.getName(), category.getDescription(), parent));
        category.setId(createdCategory.getId());
        return ResponseEntity.status(201).body(category);
    }

    public ResponseEntity<List<Category>> getAllCategories() throws RequestException {
        List<Category> categories = categoriesRepo.findAll();
        if (categories.isEmpty()) {
            throw new RequestException("Категории не найдены", 404);
        } else {
//            List<ShortInfAboutPromotion> shortInfAboutPromotions = getShortInf(promotions);
            return ResponseEntity.status(200).body(categories);
        }
    }

//    public List<ShortInfAboutPromotion> getShortInf(List<Promotion> promotions) {
//        List<ShortInfAboutPromotion> shortInfAboutPromotions = new ArrayList<>();
//        promotions.forEach((promotion -> {
//            shortInfAboutPromotions.add(new ShortInfAboutPromotion(promotion.getId(), promotion.getName(), promotion.getDescription()));
//        }));
//        return shortInfAboutPromotions;
//    }

    public ResponseEntity<Category> getCategory(Long id) throws RequestException {
        boolean isFound = categoriesRepo.existsById(id);
        if (isFound) {
            Category category = categoriesRepo.findById(id).get();
            return ResponseEntity.status(200).body(category);
        } else {
            throw new RequestException("Категория с id " + id + " не найдена", 404);
        }
    }

    public ResponseEntity<Category> updateCategory(ShortInfAboutCategory shortInfAboutCategory, Long id) throws RequestException {
        boolean isFound = categoriesRepo.existsById(id);
        if (isFound) {
            Category category = categoriesRepo.findById(id).get();
            if (shortInfAboutCategory.getName() != null) category.setName(shortInfAboutCategory.getName());
            category.setDescription(shortInfAboutCategory.getDescription());
            categoriesRepo.save(category);
            return ResponseEntity.status(200).body(category);
        } else {
            throw new RequestException("Категория с id " + id + " не найдена", 404);
        }
    }

    public ResponseEntity<Long> deleteCategory(Long id) throws RequestException {
        boolean isFound = categoriesRepo.existsById(id);
        if (isFound) {
            categoriesRepo.deleteById(id);
            return ResponseEntity.status(200).body(id);
        } else {
            throw new RequestException("Категория с id " + id + " не найдена", 404);
        }
    }


}
