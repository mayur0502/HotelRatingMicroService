package com.mayurTech.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mayurTech.user.service.entites.Hotel;

@FeignClient(name = "HOTELSERVICE")
public interface HotelService {

	@GetMapping("/hotels/getHotelById/{hotelID}")
	Hotel getHotel(@PathVariable("hotelID") String  hotelID);
}
