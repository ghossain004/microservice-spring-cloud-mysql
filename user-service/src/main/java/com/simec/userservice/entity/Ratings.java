package com.simec.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ratings {

    private Long ratingId;
    private Long userId;
    private Long hotelId;
    private int rating;
    private String feedback;

}
