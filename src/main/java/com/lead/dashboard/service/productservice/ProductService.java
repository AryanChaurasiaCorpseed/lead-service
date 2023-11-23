package com.lead.dashboard.service.productservice;


import com.lead.dashboard.domain.product.Product;
import com.lead.dashboard.dto.CreateProduct;

import java.util.List;
import java.util.Optional;

public interface ProductService {


    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product updateProduct(Long id, String name);

    void deleteProduct(Long id);

	Product createProduct(CreateProduct createProduct);
}
