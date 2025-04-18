package com.example.ProjectA.service;

import com.example.ProjectA.entity.User;
import com.example.ProjectA.iService.IServiceUser;
import com.example.ProjectA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServirce implements IServiceUser {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        try {
            List<User> userList =  userRepository.findAll();
            if(!userList.isEmpty()){
                System.out.println("user null");
                return null;
            }
            return userList;
        } catch (Exception e) {
            System.out.println("Error when get all user" + e.getMessage());
            return  null;
        }

    }
}
