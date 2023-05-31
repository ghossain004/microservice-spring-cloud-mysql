package com.simec.hotelservice.service.impl;

import com.simec.hotelservice.entity.Hotel;
import com.simec.hotelservice.exception.ResourceNotFoundException;
import com.simec.hotelservice.repository.HotelRepository;
import com.simec.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository repository;


    @Override
    public Hotel createHotel(Hotel hotel) {
        return repository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return repository.findAll();
    }

    @Override
    public Hotel getById(Long hotelId) {
        return repository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel with this id not found : " + hotelId));
    }
}
