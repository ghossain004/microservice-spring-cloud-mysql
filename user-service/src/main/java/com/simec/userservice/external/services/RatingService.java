package com.simec.userservice.external.services;

import com.simec.userservice.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @PostMapping("/ratings")
    public Rating crateRating(Rating values);

    @PutMapping("/ratings/{ratingsId}")
    public Rating updateRating(@PathVariable Long ratingId, Rating rating);

    @DeleteMapping("/ratings/{ratingId}")
    public Rating deleteRating(@PathVariable Long ratingId);
}
