package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Ratings;

@Repository
public interface RatingRepository extends JpaRepository<Ratings, Long>{

	@Query(value = "SELECT * FROM ratings r WHERE r.lead_id in(:ratingList) and urls_managment_id =:productId", nativeQuery = true)
	List<Ratings> findByRatingInAndProdctId(List<String> ratingList, Long productId);
	
	@Query(value = "SELECT * FROM ratings r WHERE r.lead_id =:ratings and urls_managment_id =:productId", nativeQuery = true)
	Ratings findByRatingAndProdctId(String ratings, Long productId);
       
}
