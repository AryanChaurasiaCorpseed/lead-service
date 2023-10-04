package com.lead.dashboard.controller.productController;


import com.lead.dashboard.domain.product.Product;
import com.lead.dashboard.service.productservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/leadService/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getProduct")
    public ResponseEntity<Optional<Product>> getProductById(@RequestParam Long id) {

        Optional<Product> product = productService.getProductById(id);

        if (product != null) {
            return ResponseEntity.ok(product);
        }

        return null;
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {


        Product createdProduct = productService.createProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestParam Long id,@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
