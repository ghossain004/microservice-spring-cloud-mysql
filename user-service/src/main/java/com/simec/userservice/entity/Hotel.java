package com.simec.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    private Long hotelId;
    private String hotelName;
    private String location;
    private String about;

}
