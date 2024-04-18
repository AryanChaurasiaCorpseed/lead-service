package com.lead.dashboard.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Ratings;
import com.lead.dashboard.domain.UrlsManagment;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.dto.RatingDto;
import com.lead.dashboard.repository.RatingRepository;
import com.lead.dashboard.repository.UrlsManagmentRepo;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	RatingRepository ratingRepository;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UrlsManagmentRepo urlsManagmentRepo;
	
	
	
	@Override
	public Ratings addUserRating(RatingDto ratingDto) {
		// TODO Auto-generated method stub
		Ratings ratings = new Ratings();
		ratings.setRating(ratingDto.getRating());
		List<User> userList = userRepo.findAllById(ratingDto.getRatingsUser());
		ratings.setRatingsUser(userList);
		UrlsManagment urlsManagment = urlsManagmentRepo.findById(ratingDto.getUrlsManagmentId()).get();
		ratings.setUrlsManagment(urlsManagment);
		Ratings r=ratingRepository.save(ratings);
		return r;
	}

	


	@Override
	public List<Ratings> getAllUserRating() {
		List<Ratings>ratingList=ratingRepository.findAll();
		return ratingList;
	}




	@Override
	public List<Ratings> getAllRatingByServiceId(Long serviceId) {
		List<Ratings>ratingList=ratingRepository.findAllByUrlsManagmentId(serviceId);
		return ratingList;
	}

}
