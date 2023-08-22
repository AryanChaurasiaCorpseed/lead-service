package com.lead.dashboard.serviceImpl.productserviceimpl;


import com.lead.dashboard.domain.product.Category;
import com.lead.dashboard.repository.product.CategoryRepo;
import com.lead.dashboard.service.productservice.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryImpl implements CategoryService
{
    @Autowired
    private CategoryRepo categoryRepo;

    public Category getCategoryById(Long categoryId) {
        return categoryRepo.findById(categoryId).orElse(null);
    }
    @Override
    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Optional<Category> getCategory(Long categoryId) {
        return categoryRepo.findById(categoryId);
    }

    public Category updateCategory(Long categoryId, String newCategoryName) {

        Optional<Category> optionalCategory = categoryRepo.findById(categoryId);

        System.out.println("Hitted"+optionalCategory);
        if (optionalCategory.isPresent()) {

            Category category = optionalCategory.get();
            category.setCategoryName(newCategoryName);
            return categoryRepo.save(category);
        }
        return null;
    }


    @Override
    public boolean deleteCategory(Long categoryId) {


        if (categoryRepo.existsById(categoryId))

        {
            categoryRepo.deleteById(categoryId);
            return true;
        }
        return false;
    }


    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
