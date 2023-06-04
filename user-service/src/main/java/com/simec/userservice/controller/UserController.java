package com.simec.userservice.controller;

import com.simec.userservice.entity.User;
import com.simec.userservice.service.UserService;
import com.simec.userservice.service.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    int retryCount = 1;

    @Autowired
    private UserService service;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        //check if the userName already exits
        User existingUser = service.getUserName(user.getUserName());
        if (existingUser != null){
            throw new RuntimeException("User Name (" + user.getUserName() + ") already exist!!" );
        }

        User user1 = service.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = service.getAllUser();
        return ResponseEntity.ok(allUser);
    }

//    @GetMapping("/getAll/ratings")
//    @CircuitBreaker(name = "hotelRating", fallbackMethod = "hotelRatingFallback")
//    public ResponseEntity<List<User>> getAllUsers(){
//        List<User> allUser = service.getAllUsers();
//        return ResponseEntity.ok(allUser);
//    }
//
//    public ResponseEntity<User> hotelRatingFallback(User user, Exception ex){
//        User users = User.builder()
//                .email("test@test.com")
//                .firstName("test")
//                .address("Uttara")
//                .userName("test")
//                .build();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

    @GetMapping("/getAll/ratings")
//    @CircuitBreaker(name = "hotelRating", fallbackMethod = "hotelRatingFallback")
    @Retry(name = "hotelRating", fallbackMethod = "hotelRatingFallback")
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Retry Count: {}", retryCount);
        retryCount++;
        List<User> allUser = service.getAllUsers();
        return ResponseEntity.ok(allUser);
    }

    @GetMapping("/get/{userId}")
//    @CircuitBreaker(name = "hotelRating", fallbackMethod = "hotelRatingFallback")
    @Retry(name = "hotelRating", fallbackMethod = "hotelRatingFallback")
    public ResponseEntity<User> getUser(@PathVariable Long userId){
        logger.info("Retry Count: {}", retryCount);
        retryCount++;
        User user = service.getUser(userId);
        return ResponseEntity.ok(user);
    }
    public ResponseEntity<List<User>> hotelRatingFallback(Exception ex) {
        String errorMessage = "Error occurred while retrieving user ratings: " + ex.getMessage();

        User fallbackUser = User.builder()
                .email("test@test.com")
                .firstName("test")
                .address("Uttara")
                .userName("test")
                .build();

        // Optionally, you can log the exception message
        logger.error(errorMessage);

        return ResponseEntity.ok(Collections.singletonList(fallbackUser));
    }

//    public ResponseEntity<User> hotelRatingFallback(Long userId, Exception ex){
//        User user = User.builder()
//                .email("test@test.com")
//                .firstName("test")
//                .address("Uttara")
//                .userName("test")
//                .build();
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user){
        User existingUser = service.getUser(userId);
        if (existingUser != null){
            user.setUserId(userId);
            User existingUserName = service.getUserName(user.getUserName());
            if (existingUserName != null){
                throw new RuntimeException("User Name (" + user.getUserName() + ") already exist!!" );
            }
            User updatedUser = service.updateUser(user);
            return ResponseEntity.ok(updatedUser);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        User existingUser = service.getUser(userId);
        if (existingUser != null){
            service.deleteUser(userId);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted");
        }
        return ResponseEntity.noContent().build();
    }
}
