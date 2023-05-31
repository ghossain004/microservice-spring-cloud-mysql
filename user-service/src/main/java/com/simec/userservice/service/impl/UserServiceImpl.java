package com.simec.userservice.service.impl;

import com.simec.userservice.entity.User;
import com.simec.userservice.repository.UserRepository;
import com.simec.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private RestTemplate template;

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
        template.getForObject("http://localhost:8083/api/rating/user/{userId}", ArrayList.class);

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
