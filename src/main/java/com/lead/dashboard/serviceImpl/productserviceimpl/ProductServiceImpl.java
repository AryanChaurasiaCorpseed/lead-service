package com.lead.dashboard.serviceImpl.productserviceimpl;


import com.lead.dashboard.domain.product.Product;
import com.lead.dashboard.repository.product.ProductRepo;
import com.lead.dashboard.service.productservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {

        return productRepo.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
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


