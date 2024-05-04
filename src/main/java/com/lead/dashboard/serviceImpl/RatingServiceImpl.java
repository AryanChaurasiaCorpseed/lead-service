package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Ratings;
import com.lead.dashboard.domain.UrlsManagment;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.dto.RatingDto;
import com.lead.dashboard.dto.UpdateRatingDto;
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
		Ratings ratings=ratingRepository.findAllByRatingAndUrlsManagmentId(ratingDto.getRating(),ratingDto.getUrlsManagmentId());
		if(ratings ==null) {
			 ratings = new Ratings();
			ratings.setRating(ratingDto.getRating());
			List<User> userList = userRepo.findAllById(ratingDto.getRatingsUser());
			ratings.setRatingsUser(userList);
			UrlsManagment urlsManagment = urlsManagmentRepo.findById(ratingDto.getUrlsManagmentId()).get();
			ratings.setUrlsManagment(urlsManagment);
			Ratings r=ratingRepository.save(ratings);
		}else {
			List<User> userList = userRepo.findAllById(ratingDto.getRatingsUser());
			List<User> ratingUser = ratings.getRatingsUser();
			ratingUser.addAll(userList);
			ratings.setRatingsUser(ratingUser);
			UrlsManagment urlsManagment = urlsManagmentRepo.findById(ratingDto.getUrlsManagmentId()).get();
			ratings.setUrlsManagment(urlsManagment);
			Ratings r=ratingRepository.save(ratings);
		}
		return ratings;
	}

	


	@Override
	public List<Map<String,Object>> getAllUserRating() {
		List<Ratings>ratingList=ratingRepository.findAll();
		List<Map<String,Object>>arr = new ArrayList<>();
		for(Ratings r:ratingList) {
			Map<String,Object>map=new HashMap<>();
			map.put("id", r.getId());
			map.put("rating", r.getRating());
			map.put("serviceName", r.getUrlsManagment().getUrlsName());
			List<String>userList=r.getRatingsUser().stream().map(i->i.getFullName()).collect(Collectors.toList());
			map.put("assignee", userList);
			arr.add(map);
		}
		return arr;
	}




	@Override
	public List<Ratings> getAllRatingByServiceId(Long serviceId) {
		List<Ratings>ratingList=ratingRepository.findAllByUrlsManagmentId(serviceId);
		return ratingList;
	}




	@Override
	public Ratings addUserInRatingService(Long ratingId, Long serviceId, Long userId) {
		Ratings rating=ratingRepository.findAllByIdUrlsManagmentId(ratingId,serviceId);
		User user = userRepo.findById(userId).get();
		 List<User> ratingList = rating.getRatingsUser();
		 ratingList.add(user);
		 rating.setRatingsUser(ratingList);
		 ratingRepository.save(rating);
		return rating;
	}




	@Override
	public Ratings updateUserRatingService(UpdateRatingDto updateRatingDto) {
		Optional<Ratings> opRating = ratingRepository.findById(updateRatingDto.getRatingId());
		if(opRating!=null && opRating.get()!=null) {
			Ratings rating = opRating.get();
			rating.setRating(updateRatingDto.getName());
			Optional<UrlsManagment> urls = urlsManagmentRepo.findById(updateRatingDto.getRatingId());
			rating.setUrlsManagment(urls.get());
			List<User> userList = userRepo.findUAllByUserIdIn(updateRatingDto.getUserId());
			rating.setRatingsUser(userList);
			ratingRepository.save(rating);
			return rating;
		}
		return null;
	}

}
