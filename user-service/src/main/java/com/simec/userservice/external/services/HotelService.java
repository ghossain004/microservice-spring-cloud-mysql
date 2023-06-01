package com.simec.userservice.external.services;

import com.simec.userservice.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/api/hotel/get/{hotelId}")
    Hotel getHotel(@PathVariable Long hotelId);
}
