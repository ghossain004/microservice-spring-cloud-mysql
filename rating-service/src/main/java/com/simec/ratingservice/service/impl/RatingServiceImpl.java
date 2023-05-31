package com.simec.ratingservice.service.impl;

import com.simec.ratingservice.entity.Rating;
import com.simec.ratingservice.repository.RatingRepository;
import com.simec.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository repository;
    @Override
    public Rating create(Rating rating) {
        return repository.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        return repository.findAll();
    }

    @Override
    public List<Rating> getAllByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<Rating> getAllByHotelId(Long hotelId) {
        return repository.findByHotelId(hotelId);
    }
}
