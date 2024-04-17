package com.lead.dashboard.controller.ratingController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Ratings;
import com.lead.dashboard.dto.RatingDto;
import com.lead.dashboard.service.RatingService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class RatingController {
	
	@Autowired
	RatingService ratingService;
	
	@GetMapping(UrlsMapping.ADD_USER_RATING)
	public Ratings addUserRating(@RequestBody RatingDto ratingDto)
	{
		Ratings rating=ratingService.addUserRating(ratingDto);
		
		return rating;		 

	}
}
