package com.mayurTech.rating.service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mayurTech.rating.service.entity.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {

	//Customs Finder Method
	
	List<Rating> findByUserId(String userId);
	List<Rating> findByHotelId(String hotelId);
	
}
