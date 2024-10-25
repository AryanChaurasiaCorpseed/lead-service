package com.lead.dashboard.service.productservice;


import com.lead.dashboard.controller.leadController.ProductImportDto;
import com.lead.dashboard.domain.product.Product;
import com.lead.dashboard.dto.AddProductAmountDto;
import com.lead.dashboard.dto.CreateProduct;
import com.lead.dashboard.dto.DocProductDto;
import com.lead.dashboard.dto.StageDto;
import com.lead.dashboard.dto.TatAndDescDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {


    List<Product> getAllProducts();

    Map<String,Object> getProductById(Long id);

    Product updateProduct(Long id, String name);

    Boolean deleteProduct(Long id);

	Product createProduct(CreateProduct createProduct);

	List<Product> getProductByUser(Long id);

	Boolean importProductByUrls(ProductImportDto productImportDto);

	Boolean createStageInProduct(StageDto stageDto);

	Boolean addDocumentsInProduct(DocProductDto docProductDto);

	Boolean addAmountInProduct(AddProductAmountDto addProductAmountDto);

	Boolean addTatAndDescription(TatAndDescDto tatAndDescDto);

	List<Map<String, Object>> getAllProductList();
}
