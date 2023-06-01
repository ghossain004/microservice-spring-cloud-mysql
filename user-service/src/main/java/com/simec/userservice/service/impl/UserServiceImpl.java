package com.simec.userservice.service.impl;

import com.simec.userservice.entity.Hotel;
import com.simec.userservice.entity.Rating;
import com.simec.userservice.entity.User;
import com.simec.userservice.external.services.HotelService;
import com.simec.userservice.repository.UserRepository;
import com.simec.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository repository;
    @Autowired
    private RestTemplate template;

    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    public User getUser(Long userId) {
        User user = repository.findById(userId).orElseThrow(() -> new RecoverableDataAccessException("User with this id not found: " + userId));

        //fetch rating
        Rating[] ratingsOfUser = template.getForObject("http://RATING-SERVICE/api/ratings/user/" + user.getUserId(), Rating[].class);
        logger.info("{} ", ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList =  ratings.stream().map(rating -> {
//            ResponseEntity<Hotel> forEntity = template.getForEntity("http://HOTEL-SERVICE/api/hotel/get/" + rating.getHotelId(), Hotel.class);
//            Hotel hotel = forEntity.getBody();
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect((Collectors.toList()));
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public User updateUser(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User getUserName(String userName) {
        return repository.findByUserName(userName);
    }


}
