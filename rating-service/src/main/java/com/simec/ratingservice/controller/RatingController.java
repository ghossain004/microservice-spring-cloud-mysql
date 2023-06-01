package com.simec.ratingservice.controller;

import com.simec.ratingservice.entity.Rating;
import com.simec.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService service;

    @PostMapping("/create")
    public ResponseEntity<Rating> saveRatings(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(rating));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(service.getAllByUserId(userId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable Long hotelId){
        return ResponseEntity.ok(service.getAllByHotelId(hotelId));
    }

}
