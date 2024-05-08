package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Slug;



@Repository
public interface SlugRepository extends JpaRepository<Slug, Long> {

	@Query(value = "SELECT * FROM slug s WHERE s.id in(:slugId)", nativeQuery = true)
	List<Slug> findAllByIdIn(List<Long> slugId);

}
