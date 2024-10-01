package com.lead.dashboard.repository.product;

import com.lead.dashboard.domain.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long>

{

}
