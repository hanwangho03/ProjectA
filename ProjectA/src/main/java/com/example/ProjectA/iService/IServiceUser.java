package com.example.ProjectA.iService;

import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;


public interface IServiceUser {
     ResponseEntity<?> getAllUser();
}
