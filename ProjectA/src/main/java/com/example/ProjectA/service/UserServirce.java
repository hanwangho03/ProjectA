package com.example.ProjectA.service;

import com.example.ProjectA.entity.User;
import com.example.ProjectA.iService.IRepositoryUser;
import com.example.ProjectA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServirce implements IRepositoryUser {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        List<User> userList =  userRepository.findAll();
        return userList;
    }
}
