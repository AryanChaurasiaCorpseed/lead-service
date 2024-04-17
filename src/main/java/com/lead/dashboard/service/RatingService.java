package com.lead.dashboard.service;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Ratings;
import com.lead.dashboard.dto.RatingDto;

@Service
public interface RatingService {

	Ratings addUserRating(RatingDto ratingDto);

}
