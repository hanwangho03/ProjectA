package com.example.ProjectA.service;

import com.example.ProjectA.Helper.Response;
import com.example.ProjectA.entity.User;
import com.example.ProjectA.iService.IServiceUser;
import com.example.ProjectA.repository.UserRepository;
import com.google.common.hash.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;


@Service
public class UserServirce implements IServiceUser {
    @Autowired
    private UserRepository userRepository;

    private BloomFilter<String> RBloomFilter;

    @PostConstruct
    public void loadBloomFilter (){
        RBloomFilter = BloomFilter.create(
                Funnels.stringFunnel(StandardCharsets.UTF_8),
                100000,
                0.01
        );

        List<User> userList = userRepository.findAll();
        if(!userList.isEmpty()){
            userList.forEach(user -> {RBloomFilter.put(user.getEmail());});
        }

    }

    @Async
    public ResponseEntity<?> getAllUser() {
        try {
            List<User> userList =  userRepository.findAll();
            if(!userList.isEmpty()){
                System.out.println("user null");
                return null;
            }

            Response<List<User>>  response = new Response<>(
                    true,"Get All user sucessfully",userList
            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            System.out.println("Error when get all user" + e.getMessage());
            Response<List<User>>  response = new Response<>(
                    false,"Exception when GetAllUser" + e.getMessage(),null
            );
            return  ResponseEntity.status(500).body(response);
        }
    }
}
