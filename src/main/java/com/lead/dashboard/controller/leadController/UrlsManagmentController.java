package com.lead.dashboard.controller.leadController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Role;
import com.lead.dashboard.domain.Slug;
import com.lead.dashboard.domain.UrlsManagment;
import com.lead.dashboard.dto.EditUrlsDto;
import com.lead.dashboard.dto.UrlsDto;
import com.lead.dashboard.repository.SlugRepository;
import com.lead.dashboard.repository.UrlsManagmentRepo;

@RestController
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
		List<Slug>slugList=slugRepository.findAllByIdIn(urlsDto.getSlugId());
		urlsManagment.setUrlSlug(slugList);
		urlsManagmentRepo.save(urlsManagment);
		return urlsManagment;
	}
	
	@PutMapping("/urls/updateUrls")
	public 	UrlsManagment updateUrls(@RequestBody EditUrlsDto editUrlsDto) {
		UrlsManagment urlsManagment = urlsManagmentRepo.findById(editUrlsDto.getUrlsId()).get();
		urlsManagment.setUrlsName(editUrlsDto.getName());
		urlsManagment.setQuality(editUrlsDto.isQuality());
		List<Slug>slugList=slugRepository.findAllByIdIn(editUrlsDto.getSlugId());
		urlsManagment.setUrlSlug(slugList);
		urlsManagmentRepo.save(urlsManagment);
		return urlsManagment;
	}
	
	
	@GetMapping("/urls/getUrls")
	public 	List<UrlsManagment> getUrls() {	
		List<UrlsManagment> urls = urlsManagmentRepo.findAll();
		return urls;
	}
	
	
}
