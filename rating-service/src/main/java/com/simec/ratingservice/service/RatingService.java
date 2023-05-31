package com.simec.ratingservice.service;

import com.simec.ratingservice.entity.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating create(Rating rating);

    //list
    List<Rating> getAllRating();

    //get all by userId
    List<Rating> getAllByUserId(Long userId);

    //get all by hotelId
    List<Rating> getAllByHotelId(Long hotelId);
}
