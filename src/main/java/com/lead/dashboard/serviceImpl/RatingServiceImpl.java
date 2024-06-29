package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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
			List<User> uList = allUserRating(ratingDto.getUrlsManagmentId());
			List<User> users=new ArrayList<>();
			for(User u:userList) {
				if(!uList.contains(u)) {
					users.add(u);
				}
			}
			System.out.println(users+" . .  users");
			ratings.setRatingsUser(users);
			UrlsManagment urlsManagment = urlsManagmentRepo.findById(ratingDto.getUrlsManagmentId()).get();
			ratings.setUrlsManagment(urlsManagment);
			Ratings r=ratingRepository.save(ratings);
		}else {
			List<User> userList = userRepo.findAllById(ratingDto.getRatingsUser());
			List<User> ratingUser = ratings.getRatingsUser();
			ratingUser.addAll(userList);
	        Set<User>userRating = new HashSet<>(ratingUser);
			ratings.setRatingsUser(new ArrayList<>(userRating));
			UrlsManagment urlsManagment = urlsManagmentRepo.findById(ratingDto.getUrlsManagmentId()).get();
			ratings.setUrlsManagment(urlsManagment);
			Ratings r=ratingRepository.save(ratings);
		}
		return ratings;
	}
     public List<User> allUserRating(Long id){
 		List<Ratings> ratings=ratingRepository.findAllByUrlsManagmentId(id);
 		List<User>userList=new ArrayList<>();
 		for(Ratings r:ratings) {
 			userList.addAll(r.getRatingsUser());			
 		}
        return userList;
     }
     public List<User> allUserRatingV2(Long id,String rating){
  		List<Ratings> ratings=ratingRepository.findAllByUrlsManagmentId(id);
  		List<User>userList=new ArrayList<>();
  		for(Ratings r:ratings) {
  			if(!rating.equals(r.getRating())) {
  	  			userList.addAll(r.getRatingsUser());			

  			}
//  			userList.addAll(r.getRatingsUser());			
  		}
         return userList;
      }
 	@Override
 	public Ratings updateUserRatingService(UpdateRatingDto updateRatingDto) {
 		Optional<Ratings> opRating = ratingRepository.findById(updateRatingDto.getRatingId());
 		if(opRating!=null && opRating.get()!=null) {
 			Ratings rating = opRating.get();
 			rating.setRating(updateRatingDto.getRating());
 			Optional<UrlsManagment> urls = urlsManagmentRepo.findById(updateRatingDto.getRatingId());
 			rating.setUrlsManagment(urls.get());
 			List<User> userList = userRepo.findUAllByUserIdIn(updateRatingDto.getRatingsUser());
 			
 			List<User> uList = allUserRating(updateRatingDto.getUrlsManagmentId());
			List<User> users=new ArrayList<>();
			for(User u:userList) {
				if(!uList.contains(u)) {
					users.add(u);
				}
			}
 			rating.setRatingsUser(users);
 			ratingRepository.save(rating);
 			return rating;
 		}
 		return null;
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
			Map<Long, Object> userList = r.getRatingsUser().stream().collect(Collectors.toMap(i->i.getId(), i->i.getFullName()));

//			List<String>userList=r.getRatingsUser().stream().map(i->i.getFullName()).collect(Collectors.toList());
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




//	@Override
//	public Ratings updateUserRatingService(UpdateRatingDto updateRatingDto) {
//		Optional<Ratings> opRating = ratingRepository.findById(updateRatingDto.getRatingId());
//		if(opRating!=null && opRating.get()!=null) {
//			Ratings rating = opRating.get();
//			rating.setRating(updateRatingDto.getRating());
//			Optional<UrlsManagment> urls = urlsManagmentRepo.findById(updateRatingDto.getRatingId());
//			rating.setUrlsManagment(urls.get());
//			List<User> userList = userRepo.findUAllByUserIdIn(updateRatingDto.getRatingsUser());
//			rating.setRatingsUser(userList);
//			ratingRepository.save(rating);
//			return rating;
//		}
//		return null;
//	}




	@Override
	public List<Map<String, Object>> getRetingByUrls(Long urlsId) {
		List<Ratings> ratingList = ratingRepository.findByUrlsManagmentId(urlsId);
        List<Map<String,Object>>result = new ArrayList<>();
        for(Ratings r:ratingList) {
        	Map<String,Object>map= new HashMap<>();
        	map.put("id", r.getId());
        	map.put("rating", r.getRating());
        	map.put("user", r.getRatingsUser()!=null?r.getRatingsUser().stream().map(i->i.getFullName()).collect(Collectors.toList()):null);
        	map.put("urlsName", r.getUrlsManagment().getUrlsName());
            result.add(map);
        }
		return result;
	}

}
