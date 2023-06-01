package com.simec.hotelservice.controller;

import com.simec.hotelservice.entity.Hotel;
import com.simec.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService service;

    @PostMapping("/create")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createHotel(hotel));
    }

    @GetMapping("/get/{hotelId}")
    public ResponseEntity<Hotel> getById(@PathVariable Long hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(hotelId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Hotel>> getAll(){
        return ResponseEntity.ok(service.getAllHotel());
    }


//    @PutMapping


//    @DeleteMapping
}
