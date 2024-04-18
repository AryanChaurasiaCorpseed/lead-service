package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Ratings;
import com.lead.dashboard.dto.RatingDto;

@Service
public interface RatingService {

	Ratings addUserRating(RatingDto ratingDto);

	List<Ratings> getAllUserRating();

	List<Ratings> getAllRatingByServiceId(Long serviceId);

}
