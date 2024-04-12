package com.lead.dashboard.controller.leadController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Role;
import com.lead.dashboard.domain.UrlsManagment;
import com.lead.dashboard.repository.UrlsManagmentRepo;

@RestController
public class UrlsManagmentController {
	
	@Autowired
	UrlsManagmentRepo urlsManagmentRepo;

	@PostMapping("/urls/createUrls")
	public 	UrlsManagment createUrls(@RequestParam String name,@RequestParam boolean isQuality) {
		UrlsManagment urlsManagment = new UrlsManagment();
		urlsManagment.setUrlsName(name);
		urlsManagment.setQuality(isQuality);
		urlsManagmentRepo.save(urlsManagment);
		return urlsManagment;
	}
	
	
	@GetMapping("/urls/getUrls")
	public 	List<UrlsManagment> getUrls(@RequestParam String name,@RequestParam boolean isQuality) {	
		List<UrlsManagment> urls = urlsManagmentRepo.findAll();
		return urls;
	}
	
	
}
