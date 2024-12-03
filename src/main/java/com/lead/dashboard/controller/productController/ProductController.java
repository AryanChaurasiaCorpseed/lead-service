package com.lead.dashboard.controller.productController;


import com.lead.dashboard.controller.leadController.ProductImportDto;
import com.lead.dashboard.domain.product.Product;
import com.lead.dashboard.dto.AddProductAmountDto;
import com.lead.dashboard.dto.CreateProduct;
import com.lead.dashboard.dto.DocProductDto;
import com.lead.dashboard.dto.StageDto;
import com.lead.dashboard.dto.TatAndDescDto;
import com.lead.dashboard.dto.UpdateProduct;
import com.lead.dashboard.service.productservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/leadService/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;



    
    @GetMapping("/getAllProductList")
    public List<Map<String,Object>> getAllProductList() {
        return productService.getAllProductList();
    }
 
    @GetMapping("/getProduct")
    public ResponseEntity<Map<String,Object>> getProductById(@RequestParam Long id) {

    	Map<String,Object> product = productService.getProductById(id);

        if (product != null) {
            return ResponseEntity.ok(product);
        }

        return null;
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> product = productService.getAllProducts();

        if (product != null) {
            return ResponseEntity.ok(product);
        }

        return null;
    }
    @GetMapping("/getProductByUser")
    public ResponseEntity<List<Product>> getProductByUser(@RequestParam Long id) {

        List<Product> product = productService.getProductByUser(id);

        if (product != null) {
            return ResponseEntity.ok(product);
        }

        return null;
    }
    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProduct createProduct) {


        Product result = productService.createProduct(createProduct);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody UpdateProduct updateProduct) {
        Product updatedProduct = productService.updateProduct(updateProduct.getId(), updateProduct.getName());
        if (updatedProduct == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/delete")
    public Boolean deleteProduct(@RequestParam Long id) {
        Boolean f=productService.deleteProduct(id);
        return f;
    }
    
    @PostMapping("/importProductByUrls")
    public ResponseEntity<Boolean> importProductByUrls(@RequestBody ProductImportDto productImportDto) {


    	Boolean result = productService.importProductByUrls(productImportDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    
    @PostMapping("/addStageInProduct")
    public ResponseEntity<Boolean> addStageInProduct(@RequestBody StageDto StageDto) {


        Boolean result = productService.createStageInProduct(StageDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    
    @PostMapping("/addDocumentsInProduct")
    public ResponseEntity<Boolean> addDocumentsInProduct(@RequestBody DocProductDto docProductDto) {


        Boolean result = productService.addDocumentsInProduct(docProductDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    
    @PostMapping("/addAmountInProduct")
    public ResponseEntity<Boolean> addAmountInProduct(@RequestBody AddProductAmountDto addProductAmountDto) {


        Boolean result = productService.addAmountInProduct(addProductAmountDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    
    
    @PostMapping("/addTatAndDescription")
    public ResponseEntity<Boolean> addTatAndDescription(@RequestBody TatAndDescDto tatAndDescDto) {


        Boolean result = productService.addTatAndDescription(tatAndDescDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    
    
    @DeleteMapping("/deleteAmountFromProduct")
    public ResponseEntity<Boolean> deleteAmountFromProduct(@RequestParam Long productAmountId) {


        Boolean result = productService.deleteAmountFromProduct(productAmountId);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @DeleteMapping("/deleteDocumentFromProduct")
    public ResponseEntity<Boolean> deleteDocumentFromProduct(@RequestParam Long productdocumentId) {


        Boolean result = productService.deleteDocumentFromProduct(productdocumentId);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @DeleteMapping("/deleteStageFromProduct")
    public ResponseEntity<Boolean> deleteStageFromProduct(@RequestParam Long productStageId) {


        Boolean result = productService.deleteStageFromProduct(productStageId);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    
    @PostMapping("/addKnowledgeDocumentsInProduct")
    public ResponseEntity<Boolean> addKnowledgeDocumentsInProduct(@RequestBody DocProductDto docProductDto) {


        Boolean result = productService.addKnowledgeDocumentsInProduct(docProductDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    
    
}
