package com.lead.dashboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Ratings;
import com.lead.dashboard.dto.RatingDto;
import com.lead.dashboard.dto.UpdateRatingDto;

@Service
public interface RatingService {

	Ratings addUserRating(RatingDto ratingDto) throws Exception;

	List<Map<String,Object>> getAllUserRating();

	List<Ratings> getAllRatingByServiceId(Long serviceId);

	Ratings addUserInRatingService(Long ratingId, Long serviceId, Long userId);

	Ratings updateUserRatingService(UpdateRatingDto updateRatingDto);

	List<Map<String, Object>> getRatingByUrls(Long urlsId);

}
