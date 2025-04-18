package com.example.ProjectA.controller;

import com.example.ProjectA.entity.User;
import com.example.ProjectA.iService.IRepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IRepositoryUser iRepositoryUser;

    @GetMapping
    public List<User> getAllUsers()  {
        try {
            return iRepositoryUser.getAllUser();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
