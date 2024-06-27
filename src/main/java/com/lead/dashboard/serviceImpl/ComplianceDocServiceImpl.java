package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.lead.dashboard.domain.product.Category;
import com.lead.dashboard.repository.product.CategoryRepo;
import com.lead.dashboard.service.ComplianceDocService;

public class ComplianceDocServiceImpl implements ComplianceDocService{

	
    @Autowired
    private CategoryRepo categoryRepo;
	
    @Override
	public List<Map<String,Object>>getAllComplianceDocuments(){
		List<Category> categoryList = categoryRepo.findAll();
		List<Map<String,Object>> res = new ArrayList<>();
		
		for(Category category:categoryList) {
			Map<String,Object>map = new HashMap<>();
			map.put("id", category.getId());
			map.put("name", category.getCategoryName());
			List<String>doc = new ArrayList<>();
			if(category.getDocuments()!=null) {
				doc.addAll (category.getDocuments());
			}
		
			map.put("documents", doc);
			res.add(map);
		}
		return res;
	}
}
