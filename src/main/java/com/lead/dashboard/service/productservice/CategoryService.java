package com.lead.dashboard.service.productservice;

import com.lead.dashboard.domain.product.Category;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    Category createCategory(Category category);

    Optional<Category> getCategory(Long categoryId);

//    Product addProductToCategory(Long categoryId, String productName);

    Category updateCategory(Long categoryId, String newCategoryName);

    boolean deleteCategory(Long categoryId);

    List<Category> getAllCategories();
}
