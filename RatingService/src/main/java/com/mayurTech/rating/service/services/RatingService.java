package com.mayurTech.rating.service.services;

import java.util.List;

import com.mayurTech.rating.service.entity.Rating;

public interface RatingService {

	Rating createRating(Rating rating);
	
	List<Rating> getAllRating();
	
	List<Rating> getRatingByUsrId(String usrId);
	
	List<Rating> getRatingByHotelId(String hotelId);
	
	
}
