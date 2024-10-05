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
	
	@Query(value = "SELECT s.id FROM slug s WHERE s.name =:name limit 1", nativeQuery = true)
	Long findIdByName(String name);

	@Query(value = "SELECT * FROM slug s WHERE s.name =:name and is_plant_setup =:b limit 1", nativeQuery = true)
	Slug findByNameAndIsPlantSetUp(String name, boolean b);

}
