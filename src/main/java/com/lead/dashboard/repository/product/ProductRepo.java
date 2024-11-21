package com.lead.dashboard.repository.product;


import com.lead.dashboard.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long>

{

	@Query(value = "SELECT * FROM product p WHERE p.product_name =:name limit 1", nativeQuery = true)
	Product findByName(String name);

}
