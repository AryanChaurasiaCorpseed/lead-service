package com.lead.dashboard.service.productservice;


import com.lead.dashboard.domain.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {


    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product createProduct(Product product);

    Product updateProduct(Long id,Product product);

    void deleteProduct(Long id);
}
