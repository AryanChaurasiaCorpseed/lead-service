package com.lead.dashboard.serviceImpl.productserviceimpl;


import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.product.Category;
import com.lead.dashboard.domain.product.Product;
import com.lead.dashboard.dto.CreateProduct;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.repository.product.CategoryRepo;
import com.lead.dashboard.repository.product.ProductRepo;
import com.lead.dashboard.service.productservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    
    @Autowired
    CategoryRepo categoryRepo;
    
	@Autowired
	UserRepo userRepo;

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
		User user = userRepo.findById(createProduct.getUserId()).get();
        Optional<Category> opCategory = categoryRepo.findById(createProduct.getCategoryId());
        Category category = opCategory.get();
    	Product product = new Product();
    	product.setProductName(createProduct.getName());
    	product.setCreatedBy(user);
    	product.setCreatedDate(new Date());
    	
    	product.setGovermentfees(createProduct.getGovermentfees());
    	product.setGovermentCode(createProduct.getGovermentCode());
    	product.setGovermentGst(createProduct.getGovermentGst());

    	product.setProfessionalFees(createProduct.getProfessionalFees());
    	product.setProfessionalCode(createProduct.getProfessionalCode());
    	product.setProfesionalGst(createProduct.getProfesionalGst());
		
    	product.setServiceCharge(createProduct.getServiceCharge());			
    	product.setServiceCode(createProduct.getServiceCode());
    	product.setServiceGst(createProduct.getServiceGst());
    	product.setOtherFees(createProduct.getOtherFees());
		
    	product.setOtherCode(createProduct.getOtherCode());
    	product.setOtherGst(createProduct.getOtherGst());
    	
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
    public Product updateProduct(Long id, String name) {

    	 Product product = productRepo.findById(id).get();
    	product.setProductName(name);
      
        return productRepo.save(product);
    }

    @Override
    public Boolean deleteProduct(Long id) {
    	Boolean flag=false;
    	Product product = productRepo.findById(id).get();
    	product.setDeleted(true);
    	productRepo.save(product);
    	flag=true;
        return flag;
    	
    }

	@Override
	public List<Product> getProductByUser(Long id) {
		
		return null;
	}

}


