package com.example.ProjectA.iService;

import org.springframework.http.ResponseEntity;

public interface IServiceRole {
    ResponseEntity<?> getAllRole();
    ResponseEntity<?> createRole();
}
