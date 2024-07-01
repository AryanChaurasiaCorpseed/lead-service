package com.lead.dashboard.controller.leadController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Slug;
import com.lead.dashboard.domain.UrlsManagment;
import com.lead.dashboard.dto.EditUrlsDto;
import com.lead.dashboard.dto.UrlsDto;
import com.lead.dashboard.repository.SlugRepository;

@RestController
@RequestMapping("/leadService/api/v1")
public class SlugController {
	
  
	@Autowired
	SlugRepository slugRepository;
	
	@PostMapping("/slug/createSlug")
	public 	Slug createSlug(@RequestParam String name) {
		Slug slug = new Slug();
		slug.setName(name);
		slugRepository.save(slug);
		return slug;
	}
	
	@PutMapping("/slug/updateSlug")
	public 	Slug updateSlug(@RequestParam String name,@RequestParam Long id) {
		 Slug slug = slugRepository.findById(id).get();
        slug.setName(name);
        slugRepository.save(slug);
		return slug;
	}
	
	
	@GetMapping("/slug/getSlug")
	public 	List<Slug> getSlug() {	
		List<Slug> urls = slugRepository.findAll();
		urls= urls.stream().sorted(Comparator.comparing(Slug::getId).reversed()).collect(Collectors.toList());
		return urls;
	}
	

}
