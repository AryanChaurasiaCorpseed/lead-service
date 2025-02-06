package com.lead.dashboard.controller.leadController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.lead.dashboard.domain.Role;
import com.lead.dashboard.domain.Slug;
import com.lead.dashboard.domain.UrlsManagment;
import com.lead.dashboard.dto.EditUrlsDto;
import com.lead.dashboard.dto.SimilarSlugDto;
import com.lead.dashboard.dto.UrlsDto;
import com.lead.dashboard.repository.SlugRepository;
import com.lead.dashboard.repository.UrlsManagmentRepo;

@RestController
@RequestMapping("/leadService/api/v1")
public class UrlsManagmentController {

	@Autowired
	SlugRepository slugRepository;

	@Autowired
	UrlsManagmentRepo urlsManagmentRepo;

	@PostMapping("/urls/createUrls")
	public 	UrlsManagment createUrls(@RequestBody UrlsDto urlsDto) {
		UrlsManagment urlsManagment = new UrlsManagment();
		urlsManagment.setUrlsName(urlsDto.getName());
		urlsManagment.setQuality(urlsDto.isQuality());
		System.out.println(urlsDto.getUrlSlug());
		List<Slug>slugList=slugRepository.findAllByIdIn(urlsDto.getUrlSlug());
		System.out.println(slugList);
		urlsManagment.setUrlSlug(slugList);
		urlsManagmentRepo.save(urlsManagment);
		return urlsManagment;
	}

	@PutMapping("/urls/updateUrls")
	public 	UrlsManagment updateUrls(@RequestBody EditUrlsDto editUrlsDto) {
		UrlsManagment urlsManagment = urlsManagmentRepo.findById(editUrlsDto.getUrlsId()).get();
		urlsManagment.setUrlsName(editUrlsDto.getName());
		urlsManagment.setQuality(editUrlsDto.isQuality());
		System.out.println(editUrlsDto.getUrlSlug()+" SLUG LIST ID");
		List<Slug>slugList=slugRepository.findAllByIdIn(editUrlsDto.getUrlSlug());
		System.out.println(slugList+"SLUG LIST");

		urlsManagment.setUrlSlug(slugList);
		urlsManagmentRepo.save(urlsManagment);
		return urlsManagment;
	}


	@GetMapping("/urls/getUrls")
	public 	List<UrlsManagment> getUrls(@RequestParam(required=false) int pageSize,@RequestParam(required=false)  int pageNo) {	

		PageRequest pageRequest = PageRequest.of(pageNo-1, pageSize);
		Page<UrlsManagment> urls = urlsManagmentRepo.findAll(pageRequest);
		List<UrlsManagment>urlsList=urls.getContent();
		return urlsList;
	}


	@GetMapping("/urls/getAllUrls")
	public 	List<UrlsManagment> getAllUrls() {	

		List<UrlsManagment> urlsList = urlsManagmentRepo.findAll();
		return urlsList;
	}


	@GetMapping("/urls/getSlugByUrlId")
	public 	List<Slug> getSlugByUrlId(@RequestParam Long id) {	
		Optional<UrlsManagment> urlsOp = urlsManagmentRepo.findById(id);
		List<Slug> urlsList = new ArrayList<>();
		if(urlsOp!=null && urlsOp.get()!=null) {
			UrlsManagment url = urlsOp.get();
			urlsList= url.getUrlSlug();

		}
		return urlsList;
	}
	@GetMapping("/urls/getGlobalSearchUrls")
	public 	List<UrlsManagment> getGlobalSearchUrls(@RequestParam String name) {	
		List<UrlsManagment> urlsList=new ArrayList<>();
		urlsList = urlsManagmentRepo.findAllGlobal(name);
		List<Long>urlsId=slugRepository.findUrlsIdGlobalSearchInSlug(name);
		if(urlsId!=null &&urlsId.size()>0) {
			List<UrlsManagment> urls = urlsManagmentRepo.findAllByIdIn(urlsId);
			urlsList.addAll(urls);
		}
		return urlsList;
	}

	@PostMapping("/urls/addSimilarSlug")
	public 	UrlsManagment addSimilarSlug(@RequestBody SimilarSlugDto similarSlugDto) {
		UrlsManagment urlsManagment = urlsManagmentRepo.findById(similarSlugDto.getId()).get();
		List<Slug> slugList = slugRepository.findAllByIdIn(similarSlugDto.getUrlSlugIds());
		urlsManagment.setUrlSimilarSlug(slugList);
		urlsManagmentRepo.save(urlsManagment);
		return urlsManagment;
	}
	
	
	@GetMapping("/urls/getSimilarSlugByUrlId")
	public 	List<Slug> getSimilarSlugByUrlId(@RequestParam Long id) {	
		Optional<UrlsManagment> urlsOp = urlsManagmentRepo.findById(id);
		List<Slug> urlsList = new ArrayList<>();
		if(urlsOp!=null && urlsOp.get()!=null) {
			UrlsManagment url = urlsOp.get();
			urlsList= url.getUrlSimilarSlug();

		}
		return urlsList;
	}
	
	
	@PutMapping("/urls/removeSlugFromUrls")
	public 	UrlsManagment removeSlugFromUrls(@RequestBody EditUrlsDto editUrlsDto) {
		UrlsManagment urlsManagment = urlsManagmentRepo.findById(editUrlsDto.getUrlsId()).get();
		List<Slug> slugList = urlsManagment.getUrlSlug();
		List<Slug>sList=new ArrayList<>();
		for(Slug s:slugList) {
			if(!editUrlsDto.getUrlSlug().contains(s.getId())) {
				sList.add(s);
			}
		}
		urlsManagment.setUrlSlug(sList);
		urlsManagmentRepo.save(urlsManagment);
		return urlsManagment;
	}
	
	@GetMapping("/urls/getUrlsNameForWebsite")
	public 	List<String> getUrlsNameForWebsite() {	
		List<String> urls = urlsManagmentRepo.findAllUrlsName();
		return urls;
	}
	
	@PostMapping("/urls/createUrlsAndSlugFromWebsite")
	public 	UrlsManagment createUrlsAndSlugFromWebsite(@RequestParam String urlsName,@RequestParam String slugName) {
		UrlsManagment urls = urlsManagmentRepo.findByUrlsName(urlsName);
		if(urls!=null) {
			Slug slug = slugRepository.findByName(slugName);
			if(slug!=null) {
				
				
			}else {
				 slug = new Slug();
				 slug.setName(slugName);
				 slug.setPlantSetup(false); 
				 slugRepository.save(slug);
				 List<Slug>sList=urls.getUrlSlug();
				 sList.add(slug);
				 urls.setUrlSlug(sList);
				urlsManagmentRepo.save(urls);

  			}
		}else {
			UrlsManagment urlsManagment = new UrlsManagment();
			urlsManagment.setQuality(true);
//			System.out.println(urlsDto.getUrlSlug());
			Slug slug=slugRepository.findByName(slugName);
			if(slug!=null) {
				List<Slug>sList=new ArrayList();
				sList.add(slug);
				urlsManagment.setUrlSlug(sList);
				urlsManagmentRepo.save(urlsManagment);
			}else {
				slug = new Slug();
				 slug.setName(slugName);
				 slug.setPlantSetup(false); 
				 slugRepository.save(slug);
				 List<Slug>sList=new ArrayList();
				 sList.add(slug);
				 urlsManagment.setUrlSlug(sList);
				 urlsManagmentRepo.save(urlsManagment);
			}

		}
		
		
//		UrlsManagment urlsManagment = new UrlsManagment();
//		urlsManagment.setUrlsName(urlsDto.getName());
//		urlsManagment.setQuality(urlsDto.isQuality());
//		System.out.println(urlsDto.getUrlSlug());
//		List<Slug>slugList=slugRepository.findAllByIdIn(urlsDto.getUrlSlug());
//		System.out.println(slugList);
//		urlsManagment.setUrlSlug(slugList);
//		urlsManagmentRepo.save(urlsManagment);
		return urls;
	}



}
