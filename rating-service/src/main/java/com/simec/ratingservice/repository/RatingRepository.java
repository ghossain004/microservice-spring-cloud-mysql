package com.simec.ratingservice.repository;

import com.simec.ratingservice.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    //custom find methods
    List<Rating> findByUserId(Long userId);
    List<Rating> findByHotelId(Long hotelId);
}
