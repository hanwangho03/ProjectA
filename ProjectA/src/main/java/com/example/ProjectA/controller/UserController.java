package com.example.ProjectA.controller;

import com.example.ProjectA.entity.User;
import com.example.ProjectA.iService.IServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173") // hoặc "http://localhost:5173" khi dev
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IServiceUser iSUser;

    @GetMapping
    public List<User> getAllUsers()  {
        try {
            return iSUser.getAllUser();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
