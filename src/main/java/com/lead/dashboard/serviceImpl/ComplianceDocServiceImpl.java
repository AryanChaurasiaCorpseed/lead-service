package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.product.Category;
import com.lead.dashboard.domain.product.Product;
import com.lead.dashboard.dto.CreateCategoryDocDto;
import com.lead.dashboard.repository.product.CategoryRepo;
import com.lead.dashboard.repository.product.ProductRepo;
import com.lead.dashboard.service.ComplianceDocService;

@Service
public class ComplianceDocServiceImpl implements ComplianceDocService{

	@Autowired
	ProductRepo productRepo;
	
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
   	public List<Map<String,Object>>getAllComplianceDocuments(){
   		List<Product> productList = productRepo.findAll();
   		List<Map<String,Object>> res = new ArrayList<>();
   		
   		for(Product product:productList) {
   			Map<String,Object>map = new HashMap<>();
   			map.put("id", product.getId());
   			map.put("name", product.getProductName());
   			List<String>doc = new ArrayList<>();
   			if(product.getDocuments()!=null) {
   				doc.addAll (product.getDocuments());
   			}
   			map.put("documents", doc);
   			res.add(map);
   		}
   		return res;
   	}
	
	@Override
	public Boolean createDocumentInCategory(Long categoryId,CreateCategoryDocDto createCategoryDocDto) {
		Boolean flag = false;
		Product product = productRepo.findById(categoryId).get();
		product.setDocuments(createCategoryDocDto.getDocList());
		productRepo.save(product);
		flag=true;
		return flag;
	}
}
