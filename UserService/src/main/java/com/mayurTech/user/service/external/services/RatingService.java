package com.mayurTech.user.service.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.mayurTech.user.service.entites.Rating;



@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

	@GetMapping("/ratings/getRatingByUsrId/{usrId}")
	public Rating[] getRatingsByUsrId(@PathVariable("usrId") String usrId);

	@GetMapping("/ratings/getAllRatings")
	public Rating[] getAllRating();
	
	@PostMapping("/ratings/createRatings")
	public Rating createRating(Rating values);  
	
	@PutMapping("/ratings/updateRatings/{ratingId}")
	public Rating updateRating(@PathVariable("ratingId") String ratingId,Rating rating);
	
	@DeleteMapping("/ratings/delteRatings/{ratingId}")
	public void delteRating(@PathVariable("ratingId") String ratingId);
}
