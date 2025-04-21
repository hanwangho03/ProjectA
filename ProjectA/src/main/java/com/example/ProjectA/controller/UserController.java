package com.example.ProjectA.controller;

import com.example.ProjectA.dto.User.UserDto;
import com.example.ProjectA.dto.User.UserLogin;
import com.example.ProjectA.dto.User.UserRegister;
import com.example.ProjectA.iService.IServiceUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:5173") // hoặc "http://localhost:5173" khi dev
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IServiceUser iSUser;

    @GetMapping
    public ResponseEntity<?> getAllUsers()  {
        return  iSUser.getAllUser();
    }

    @PostMapping("/Login")
    public ResponseEntity<?> Login(@Valid @RequestBody UserLogin user) {
        return iSUser.Login(user);
    }

    @PostMapping("/Register")
    public ResponseEntity<?> Register(@Valid @RequestBody UserRegister user) {
        return iSUser.Register(user);
    }
}
