package com.simec.hotelservice.service;

import com.simec.hotelservice.entity.Hotel;

import java.util.List;

public interface HotelService {

    //create
    Hotel createHotel(Hotel hotel);

    //getAll
    List<Hotel> getAllHotel();

    //get single
    Hotel getById(Long hotelId);
}
