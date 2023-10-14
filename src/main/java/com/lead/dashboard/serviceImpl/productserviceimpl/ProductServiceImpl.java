package com.lead.dashboard.serviceImpl.productserviceimpl;


import com.lead.dashboard.domain.product.Category;
import com.lead.dashboard.domain.product.Product;
import com.lead.dashboard.dto.CreateProduct;
import com.lead.dashboard.repository.product.CategoryRepo;
import com.lead.dashboard.repository.product.ProductRepo;
import com.lead.dashboard.service.productservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Service
//public class ProductServiceImpl implements ProductService
//{
//
//    @Autowired
//    private ProductRepo productRepo;
//
//    @Override
//    public List<Product> getAllProducts() {
//        return productRepo.findAll();
//    }
//
//    @Override
//    public Optional<Product> getProductById(Long id) {
//        return productRepo.findById(id);
//    }
//
//    @Override
//    public Product createProduct(Product product) {
//        return productRepo.save(product);
//    }
//
//    @Override
//    public Product updateProduct(Product product) {
//        product.setProductName(product.getProductName());
//        return productRepo.save(product);
//    }
//
//    @Override
//    public void deleteProduct(Long id) {
//        productRepo.deleteById(id);
//
//    }

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    
    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {

        return productRepo.findById(id);
    }

    @Override
    public Product createProduct(CreateProduct createProduct) {
        Optional<Category> opCategory = categoryRepo.findById(createProduct.getCategoryId());
        Category category = opCategory.get();
    	Product product = new Product();
    	product.setProductName(createProduct.getName());
    	productRepo.save(product);
    	List<Product> productList = category.getProducts();
    	if(productList!=null && productList.size()!=0) {
    		productList.add(product);
    		category.setProducts(productList);
    	}else {
    		List<Product> pList = new ArrayList<>();
    		pList.add(product);
    		category.setProducts(pList);

;    	}
    	categoryRepo.save(category);
        return product;
    }


    @Override
    public Product updateProduct(Long id, Product product) {

        if (!productRepo.existsById(id)) {

            return null;
        }

        product.setId(id); // Ensure the correct ID is set
        return productRepo.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

}


