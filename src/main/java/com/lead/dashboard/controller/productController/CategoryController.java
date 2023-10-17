package com.lead.dashboard.controller.productController;

import com.lead.dashboard.domain.product.Category;
import com.lead.dashboard.domain.product.Product;
import com.lead.dashboard.dto.CreateCategory;
import com.lead.dashboard.dto.UpdateCategory;
import com.lead.dashboard.service.productservice.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/leadService/api/v1/category")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/createCategory")
    public ResponseEntity<Category> createCategory(@RequestBody CreateCategory category) {
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(createdCategory);
    }

//    @GetMapping("/fetchCategory")
//    public ResponseEntity<Category> getCategoryA(@RequestParam Long categoryId) {
//        Category category = categoryService.getCategory(categoryId);
//        if (category != null) {
//            return ResponseEntity.ok(category);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @GetMapping("/category/categoryProduct")
//    public ResponseEntity<List<Product>> getCategoryProducts(@RequestParam Long categoryId) {
//        Optional<Category> category = categoryService.getCategory(categoryId);
//        if (category != null) {
//            System.out.println("Hit");
////            List<Product> products = category.get().getProducts();
//            return ResponseEntity.ok(products);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


    @PutMapping("/updateCategory")
    public ResponseEntity<Category> updateCategory(@RequestBody UpdateCategory updateCategory) {
        Category updatedCategory = categoryService.updateCategory(updateCategory.getId(), updateCategory.getName());
        if (updatedCategory != null) {

            System.out.println("controller "+ updatedCategory);
            return ResponseEntity.ok(updatedCategory);
        } else {
            return ResponseEntity.notFound().build();

        }
    }

    @DeleteMapping("/deleteCategory")

    public ResponseEntity<Void> deleteCategory(@RequestParam Long categoryId) {

        System.out.println("Controller got reached .");
        boolean deleted = categoryService.deleteCategory(categoryId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    @GetMapping("/getAllCategories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoriesData = categoryService.getAllCategories();
        if (!categoriesData.isEmpty()) {
            return ResponseEntity.ok(categoriesData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
