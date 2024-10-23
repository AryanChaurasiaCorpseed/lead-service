package com.lead.dashboard.serviceImpl.productserviceimpl;


import com.lead.dashboard.controller.leadController.ProductImportDto;
import com.lead.dashboard.domain.ProductDocuments;
import com.lead.dashboard.domain.Stages;
import com.lead.dashboard.domain.UrlsManagment;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.product.Category;
import com.lead.dashboard.domain.product.Product;
import com.lead.dashboard.dto.AddProductAmountDto;
import com.lead.dashboard.dto.CreateProduct;
import com.lead.dashboard.dto.DocProductDto;
import com.lead.dashboard.dto.StageDto;
import com.lead.dashboard.repository.UrlsManagmentRepo;
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
    UrlsManagmentRepo urlsManagmentRepo;
    
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
    	
//    	product.setGovermentfees(createProduct.getGovermentfees());
//    	product.setGovermentCode(createProduct.getGovermentCode());
//    	product.setGovermentGst(createProduct.getGovermentGst());
//
//    	product.setProfessionalFees(createProduct.getProfessionalFees());
//    	product.setProfessionalCode(createProduct.getProfessionalCode());
//    	product.setProfesionalGst(createProduct.getProfesionalGst());
//		
//    	product.setServiceCharge(createProduct.getServiceCharge());			
//    	product.setServiceCode(createProduct.getServiceCode());
//    	product.setServiceGst(createProduct.getServiceGst());
//    	product.setOtherFees(createProduct.getOtherFees());
//		
//    	product.setOtherCode(createProduct.getOtherCode());
//    	product.setOtherGst(createProduct.getOtherGst());
    	
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

    
//    @Override
    public Boolean addAmountInProduct(AddProductAmountDto addProductAmountDto) {
    	Boolean flag=false;
		User user = userRepo.findById(addProductAmountDto.getUserId()).get();
        Optional<Category> opCategory = categoryRepo.findById(addProductAmountDto.getCategoryId());
        Category category = opCategory.get();
    	Product product =productRepo.findById(addProductAmountDto.getProductId()).get();
    	
    	
    	product.setGovermentfees(addProductAmountDto.getGovermentfees());
    	product.setGovermentCode(addProductAmountDto.getGovermentCode());
    	product.setGovermentGst(addProductAmountDto.getGovermentGst());

    	product.setProfessionalFees(addProductAmountDto.getProfessionalFees());
    	product.setProfessionalCode(addProductAmountDto.getProfessionalCode());
    	product.setProfesionalGst(addProductAmountDto.getProfesionalGst());
		
    	product.setServiceCharge(addProductAmountDto.getServiceCharge());			
    	product.setServiceCode(addProductAmountDto.getServiceCode());
    	product.setServiceGst(addProductAmountDto.getServiceGst());
    	product.setOtherFees(addProductAmountDto.getOtherFees());
		
    	product.setOtherCode(addProductAmountDto.getOtherCode());
    	product.setOtherGst(addProductAmountDto.getOtherGst());
    	
    	productRepo.save(product);
    	flag=true;
        return flag;
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

	@Override
	public Boolean importProductByUrls(ProductImportDto productImportDto) {
		Boolean flag=true;
		List<UrlsManagment>urlsList=urlsManagmentRepo.findAllByIdIn(productImportDto.getUrlsId());
		for(UrlsManagment urls:urlsList) {
			if(!urls.isProduct()) {
				Product p=new Product();
				p.setProductName(urls.getUrlsName());
				p.setDeleted(false);
			    productRepo.save(p);
			    flag=false;
			}
			urls.setProduct(true);
			urlsManagmentRepo.save(urls);
		}
 		return flag;
	}

	@Override
	public Boolean createStageInProduct(StageDto stageDto) {
		boolean flag = false;
		
		Product product = productRepo.findById(stageDto.getProductId()).get();
		Stages stage= new Stages();
		stage.setName(stageDto.getName());
		stage.setNoOfDays(stageDto.getNoOfDays());
		stage.setTransferPercent(stageDto.getTransferPercent());
		stage.setPricePercent(stageDto.getPricePercent());
		List<Stages> prodList = product.getProductStage();
		prodList.add(stage);
		product.setProductStage(prodList);
		productRepo.save(product);
		flag=true;
		return flag;
	}

	@Override
	public Boolean addDocumentsInProduct(DocProductDto docProductDto) {
		Boolean flag=false;
		Product product = productRepo.findById(docProductDto.getProductId()).get();
		ProductDocuments pd = new ProductDocuments();
		pd.setDescription(docProductDto.getDescription());
		pd.setName(docProductDto.getName());
		pd.setType(docProductDto.getType());
		List<ProductDocuments> prodList=product.getProductDoc();
		prodList.add(pd);
		product.setProductDoc(prodList);
		productRepo.save(product);
		flag=true;
		return flag;


	}

}


