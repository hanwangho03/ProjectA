package com.example.ProjectA.controller;

import com.example.ProjectA.iService.IServiceRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class RoleController {
    @Autowired
    private IServiceRole iServiceRole;

    @GetMapping
    public ResponseEntity<?> getAllRole(){
        return iServiceRole.getAllRole();
    }

    @GetMapping
    public ResponseEntity<?> createRole(){
        return iServiceRole.createRole();
    }
}
