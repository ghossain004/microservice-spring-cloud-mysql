package com.simec.userservice.controller;

import com.simec.userservice.entity.User;
import com.simec.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

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

    @GetMapping("/get/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId){
        User user = service.getUser(userId);
        return ResponseEntity.ok(user);
    }

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
