package com.example.ProjectA.iService;

import com.example.ProjectA.dto.User.UserLogin;
import com.example.ProjectA.dto.User.UserRegister;
import org.springframework.http.ResponseEntity;


public interface IServiceUser {
     // ResponseEntity<?> sẽ trả về 1 object
     ResponseEntity<?> getAllUser();
     ResponseEntity<?> Login(UserLogin user);
     ResponseEntity<?> Register(UserRegister user);
}
