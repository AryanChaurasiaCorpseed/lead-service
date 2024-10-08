package com.lead.dashboard.controller.leadController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Slug;
import com.lead.dashboard.domain.UrlsManagment;
import com.lead.dashboard.dto.CreatePlantSetup;
import com.lead.dashboard.dto.EditUrlsDto;
import com.lead.dashboard.dto.UrlsDto;
import com.lead.dashboard.repository.SlugRepository;
import com.lead.dashboard.repository.UrlsManagmentRepo;

@RestController
@RequestMapping("/leadService/api/v1")
public class SlugController {
	
  
	@Autowired
	SlugRepository slugRepository;
	
	@Autowired
	UrlsManagmentRepo urlsManagmentRepo;
	
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
	public 	List<Map<String,Object>> getSlug(@RequestParam(required=false) int pageSize,@RequestParam(required=false)  int pageNo) {	

		PageRequest pageRequest = PageRequest.of(pageNo-1, pageSize);
	        //pass it to repos
	        //pagingUser.hasContent(); -- to check pages are there or not
		Page<Slug> urlsPage = slugRepository.findAll(pageRequest);
		List<Slug> urls = urlsPage.getContent();
		urls= urls.stream().sorted(Comparator.comparing(Slug::getId).reversed()).collect(Collectors.toList());
		List<Map<String,Object>>result = new ArrayList<>();
		System.out.println(urls.size());
		for(Slug s:urls) {
			Map<String,Object>map = new HashMap<>();
            map.put("id", s.getId());
            map.put("name", s.getName());
            map.put("slugList", s.getSlugList());
            map.put("isPlantSetup", s.isPlantSetup());
            result.add(map);
		}
		return result;
	}
	@GetMapping("/slug/getAllSlug")
	public 	List<Slug> getAllSlug() {	
		List<Slug> urls = slugRepository.findAll();
//		urls= urls.stream().sorted(Comparator.comparing(Slug::getId).reversed()).collect(Collectors.toList());
		return urls;
	}
	
	@GetMapping("/urls/getTotalSlugCount")
	public Long getTotalSlugCount() {
		return slugRepository.count();
	}

    @GetMapping("/urls/getTotalUrlsCount")
	public Long fetchTotalUrlsCount() {
		return urlsManagmentRepo.count();
	}
    
    
	@PutMapping("/slug/createPlantSetUp")
	public 	Boolean createPlantSetUp(@RequestBody  CreatePlantSetup createPlantSetup) {
		Boolean flag=false;
		if(createPlantSetup.isFlag()) {
		 Slug slug = slugRepository.findById(createPlantSetup.getId()).get();
		 List<Slug> slugList = slugRepository.findAllByIdIn(createPlantSetup.getSlugId());
		 slug.setSlugList(slugList);
		 slug.setPlantSetup(true);
		 slugRepository.save(slug);
		 flag=true;

		}else {
			 Slug slug = slugRepository.findById(createPlantSetup.getId()).get();
//			 List<Slug> slugList = slugRepository.findAllByIdIn(createPlantSetup.getSlugId());
			 slug.setSlugList(null);
			 slug.setPlantSetup(false);
			 slugRepository.save(slug);
			 flag=true;

		}
		 return flag;		 
	}
	
	 @GetMapping("/urls/getSlugChild")
		public List<Slug> fetchTotalUrlsCount(@RequestParam Long slugId) {
			Slug slugList = slugRepository.findById(slugId).get();
			return slugList.getSlugList();
		}
	    
	 
	 

	

}
