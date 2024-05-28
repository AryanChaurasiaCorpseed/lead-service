package com.lead.dashboard.controller.ratingController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Ratings;
import com.lead.dashboard.dto.RatingDto;
import com.lead.dashboard.dto.UpdateRatingDto;
import com.lead.dashboard.service.RatingService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class RatingController {
	
	@Autowired
	RatingService ratingService;
	
	@PostMapping(UrlsMapping.ADD_USER_AND_RATING)
	public Ratings addUserAndRating(@RequestBody RatingDto ratingDto)
	{
		Ratings rating=ratingService.addUserRating(ratingDto);
		
		return rating;		 

	}
	@GetMapping(UrlsMapping.GET_ALL_USER_RATING)
	public List<Map<String,Object>> getAllUserRating()
	{
		List<Map<String,Object>> rating=ratingService.getAllUserRating();
		
		return rating;		 

	}
	@GetMapping(UrlsMapping.GET_ALL_RATING_BY_SERVICE_ID)
	public List<Ratings> getAllRatingByServiceId(@RequestParam Long serviceId)
	{
		List<Ratings> rating=ratingService.getAllRatingByServiceId(serviceId);
		
		return rating;		 

	}
	@PutMapping(UrlsMapping.ADD_USER_IN_RATING_SERVICE)
	public Ratings addUserInRatingService(@RequestParam Long ratingId,@RequestParam Long serviceId,@RequestParam Long  userId)
	{
		Ratings rating=ratingService.addUserInRatingService(ratingId,serviceId,userId);		
		return rating;		 

	}
	
	
	@PutMapping(UrlsMapping.UPDATE_USER_RATING_SERVICE)
	public Ratings updateUserRatingService(@RequestBody UpdateRatingDto updateRatingDto)
	{
		Ratings rating=ratingService.updateUserRatingService(updateRatingDto);		
		return rating;		 

	}
	
	@GetMapping(UrlsMapping.GET_RATING_BY_URLS)
	public List<Map<String,Object>> getRetingByUrls(@RequestParam Long urlsId)
	{
		List<Map<String,Object>> rating=ratingService.getRetingByUrls(urlsId);
		
		return rating;		 

	}
	
}
