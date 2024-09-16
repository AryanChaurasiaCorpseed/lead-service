package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lead.dashboard.domain.UrlsManagment;
import org.springframework.data.repository.query.Param;

public interface UrlsManagmentRepo extends JpaRepository<UrlsManagment, Long>{

	@Query(value = "SELECT * FROM urls_managment um WHERE um.urls_name =:originalName limit 1", nativeQuery = true)
	UrlsManagment findByUrlsName(String originalName);
	
	@Query(value = "SELECT um.urls_name FROM urls_managment um left join url_slug us on um.id=us.urls_managment_id WHERE us.url_slug_id=:slugId", nativeQuery = true)
	String findNameBySlugId(Long slugId);
	
	@Query(value = "SELECT * FROM urls_managment um WHERE um.id in(:urlsId)", nativeQuery = true)
	List<UrlsManagment> findAllByIdIn(List<Long> urlsId);

	@Query("SELECT u FROM UrlsManagment u WHERE u.id = :urlId")
	UrlsManagment findUrlsManagmentById(@Param("urlId") Long urlId);

}
