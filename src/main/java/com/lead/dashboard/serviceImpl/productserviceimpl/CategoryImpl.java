package com.lead.dashboard.serviceImpl.productserviceimpl;


import com.lead.dashboard.domain.product.Category;
import com.lead.dashboard.dto.CreateCategory;
import com.lead.dashboard.repository.product.CategoryRepo;
import com.lead.dashboard.service.productservice.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryImpl implements CategoryService
{
    @Autowired
    private CategoryRepo categoryRepo;

    public Category getCategoryById(Long categoryId) {
        return categoryRepo.findById(categoryId).orElse(null);
    }
    @Override
    public Category createCategory(CreateCategory createCategory) {
    	Category category = new Category();
    	category.setCategoryName(createCategory.getName());
    	category.setCreatedDate(new Date());
    	category.setDocuments(createCategory.getDocuments());
        return categoryRepo.save(category);
    }
    @Override
    public Optional<Category> getCategory(Long categoryId) {
        return categoryRepo.findById(categoryId);
    }

    public Category updateCategory(Long categoryId, String newCategoryName) {

        Optional<Category> optionalCategory = categoryRepo.findById(categoryId);

        System.out.println("Hitted"+optionalCategory);
        if (optionalCategory!=null && optionalCategory.isPresent()) {

            Category category = optionalCategory.get();
            category.setCategoryName(newCategoryName);
            return categoryRepo.save(category);
        }
        return null;
    }


    @Override
    public boolean deleteCategory(Long categoryId) {

      Category category = categoryRepo.findById(categoryId).get();
      category.setDeleted(true);
      categoryRepo.save(category);
      return true;
    }


    @Override
    public Category getCategories(Long id) {
        Category  category= categoryRepo.findById(id).get();
        return category;
    }
    
    public List<Category> getAllCategories() {
        return categoryRepo.findAll().stream().filter(i->!(i.isDeleted())).collect(Collectors.toList());
    }
}
