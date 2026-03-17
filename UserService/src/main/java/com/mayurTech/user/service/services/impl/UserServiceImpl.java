package com.mayurTech.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mayurTech.user.service.controllers.UserController;
import com.mayurTech.user.service.entites.User;
import com.mayurTech.user.service.entites.Hotel;
import com.mayurTech.user.service.entites.Rating;
import com.mayurTech.user.service.exception.ResourceNotFoundException;
import com.mayurTech.user.service.external.services.HotelService;
import com.mayurTech.user.service.external.services.RatingService;
import com.mayurTech.user.service.repository.UserRepository;
import com.mayurTech.user.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private UserRepository userServiceRepository;

	private RestTemplate restTemplate;
	private HotelService hotelService;
	private RatingService ratingService;

	public UserServiceImpl(UserRepository userServiceRepository, RestTemplate restTemplate, HotelService hotelService,
			RatingService ratingService) {
		this.userServiceRepository = userServiceRepository;
		this.restTemplate = restTemplate;
		this.hotelService = hotelService;
		this.ratingService = ratingService;
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		List<Rating> updatedRating = new ArrayList();

		List<Rating> rating = user.getRating();
		for (Rating rating2 : rating) {
			rating2.setUserId(userId);
			Rating rating3 = ratingService.createRating(rating2);
			updatedRating.add(rating3);
		}
		User save = userServiceRepository.save(user);
		save.setRating(updatedRating);
		return save;

	}

	@CircuitBreaker(name = "getAllUserRatingHotelBreaker", fallbackMethod = "getAllUserFallback")
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		// Fetch Rating from different server
		List<User> users = userServiceRepository.findAll();
//		Rating[] ratings = restTemplate.getForObject("http://RATINGSERVICE/ratings/getAllRatings", Rating[].class);

		Rating[] ratings = ratingService.getAllRating();

		List<Rating> ratingList = Arrays.asList(ratings);
		users.forEach(user -> {
			List<Rating> userRating = ratingList.stream().filter(rating -> rating.getUserId().equals(user.getUserId()))
					.toList();
			List<Rating> ratingLists = userRating.stream().map(rating -> {

//				Hotel hotel = restTemplate
//						.getForObject("http://HOTELSERVICE/hotels/getHotelById/" + rating.getHotelId(), Hotel.class);

				Hotel hotel = hotelService.getHotel(rating.getHotelId());

				rating.setHotel(hotel);
				return rating;
			}).collect(Collectors.toList());

			user.setRating(ratingLists);

		});

		return users;
	}

	// creating fallback method for circuit breaker

	public List<User> getAllUserFallback(Exception ex) {
		List<User> user = new ArrayList<User>();

		User users = User.builder().email("dummy@dummy.com").name("Dummy")
				.about("This is fallback method to handle fallback torance").userId(null).build();
		user.add(users);
		return user;
	}

	int retryCount = 1;
//	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//	@Retry(name ="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")

	@RateLimiter(name = "ratingHotelRateLimiter", fallbackMethod = "ratingHotelFallback")
	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		System.out.println(userId);
		System.out.println("Retry attempt :: " + retryCount);
		retryCount++;
		User user1 = userServiceRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with given id not server " + userId));

		// Fetch reating details from below urls
		// http://localhost:8083/ratings/getRatingByUsrId/12115da3-c5ee-4e80-9ecd-a628a3097064

//		Rating[] ratingArr = restTemplate
//				.getForObject("http://RATINGSERVICE/ratings/getRatingByUsrId/" + user1.getUserId(), Rating[].class);
		Rating[] ratingArr = ratingService.getRatingsByUsrId(userId);

		logger.info("Rating object" + ratingArr);
		List<Rating> ratingsForUser = Arrays.asList(ratingArr);
		List<Rating> ratingList = ratingsForUser.stream().map(rating -> {

			// Api call to hotel service to get hotel data
			// http://localhost:8082/hotels/getHotelById/464a4f48-0253-4525-b49a-916d81bd6cba
			// set hotel to rating
			// retrun rating
//			Hotel hotel = restTemplate.getForObject("http://HOTELSERVICE/hotels/getHotelById/" + rating.getHotelId(),
//					Hotel.class);
			Hotel hotel = hotelService.getHotel(rating.getHotelId());

			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());

		user1.setRating(ratingList);
		return user1;
	}
	// creating fallback method for circuit breaker

	public User ratingHotelFallback(String userId, Exception ex) {
		logger.info("Fallback occured beacuase service is down" + ex.getMessage());
		User user = User.builder().email("mayur@mayurtech.in").name("dummy")
				.about("This user is created beacuse some service is down").userId("12345").build();
		return user;
	}

}
