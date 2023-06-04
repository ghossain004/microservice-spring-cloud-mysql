package com.simec.userservice.service;

import com.simec.userservice.entity.User;

import java.util.List;

public interface UserService {
    //for user create
    User saveUser(User user);

    //for get all user
    List<User> getAllUser();
    List<User> getAllUsers();

    // for get single user
    User getUser(Long userId);

    //for update user
    User updateUser(User user);

    //for delete user
    void deleteUser(Long id);

    //for userName check
    User getUserName(String userName);
}
