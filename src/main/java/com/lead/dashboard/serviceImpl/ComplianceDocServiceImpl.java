package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.ProductDocuments;
import com.lead.dashboard.domain.product.Category;
import com.lead.dashboard.domain.product.Product;
import com.lead.dashboard.dto.CreateCategoryDocDto;
import com.lead.dashboard.repository.ProductDocumentRepo;
import com.lead.dashboard.repository.product.CategoryRepo;
import com.lead.dashboard.repository.product.ProductRepo;
import com.lead.dashboard.service.ComplianceDocService;

@Service
public class ComplianceDocServiceImpl implements ComplianceDocService{

	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	ProductDocumentRepo productDocumentRepo;
	
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
//   			if(product.getDocuments()!=null) {
//   				doc.addAll (product.getDocuments());
//   			}
//   			map.put("documents", doc);
   			
   			map.put("documents", product.getProductKnowledgeDocument().stream().map(i->i.getName()).collect(Collectors.toList()));

   			res.add(map);
   		}
   		return res;
   	}
	
//	@Override
	public Boolean createDocumentInCategoryV2(Long categoryId,String docList) {
		Boolean flag = false;
		Product product = productRepo.findById(categoryId).get();
		List<String>doc=product.getDocuments();
		if(doc!=null) {
			doc.add(docList);
		}else {
			doc=new ArrayList<>();
			doc.add(docList);
			product.setDocuments(doc);
		}
		
		productRepo.save(product);
		flag=true;
		return flag;
	}
	
	@Override
	public Boolean createDocumentInCategory(Long categoryId,String docList) {
		Boolean flag = false;
		Product product = productRepo.findById(categoryId).get();
		List<ProductDocuments>doc=product.getProductDoc();
		if(doc!=null) {
			ProductDocuments pd= new ProductDocuments();
			pd.setName(docList);
			productDocumentRepo.save(pd);
			doc.add(pd);
			product.setProductDoc(doc);

		}else {
			doc=new ArrayList<>();
			ProductDocuments pd= new ProductDocuments();
			pd.setName(docList);
			productDocumentRepo.save(pd);

			doc.add(pd);
			product.setProductDoc(doc);
		}
		
		productRepo.save(product);
		flag=true;
		return flag;
	}
	
	  @Override
	   	public List<Map<String,Object>>getAllComplianceDocuments(int page, int size){
			Pageable pageable = PageRequest.of(page, size);

	   		List<Product> productList = productRepo.findAll(pageable).getContent();
	   		List<Map<String,Object>> res = new ArrayList<>();
	   		
	   		for(Product product:productList) {
	   			Map<String,Object>map = new HashMap<>();
	   			map.put("id", product.getId());
	   			map.put("name", product.getProductName());
	   			
//	   			List<String>doc = new ArrayList<>();
//	   			product.getProductDoc();
//	   			if(product.getDocuments()!=null) {
//	   				doc.addAll (product.getDocuments());
//	   			}
	   			map.put("documents", product.getProductKnowledgeDocument().stream().map(i->i.getName()).collect(Collectors.toList()));
	   			res.add(map);
	   		}
	   		return res;
	   	}

	@Override
	public void moveComplianceDoc() {
		List<Product> productList = productRepo.findAll();
		for(Product p:productList) {
			List<ProductDocuments>proDoc = new ArrayList<>();
			
			List<String> doc = p.getDocuments();
			if(doc!=null) {
			for(String pd:doc) {
				ProductDocuments pDoc = new ProductDocuments();
				pDoc.setName(pd);
				ProductDocuments pro = productDocumentRepo.save(pDoc);
				proDoc.add(pro);                                                                                                         
			}
			}
			p.setProductDoc(proDoc);
			productRepo.save(p);
			
		}
		
	}

	@Override
	public Map<String, Object> getComplianceDocByName(String name) {
		Product p=productRepo.findByName(name);
		Map<String, Object>res=new HashMap<>();
		if(p!=null) {
			res.put("name", p.getProductName());
			res.put("productDoc", p.getProductKnowledgeDocument());
		}
	
		return res;
	}
	
}
