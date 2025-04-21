package com.example.ProjectA.service;

import com.example.ProjectA.Helper.Response;
import com.example.ProjectA.dto.UserDto;
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
import java.util.stream.Collectors;

import static com.example.ProjectA.Mapper.UserMapper.UserToUserMapper;


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
            if(userList.isEmpty()){
                System.out.println("user null");
                Response<List<User>> response = new Response<>(false, "No users found", null);
                return ResponseEntity.status(404).body(response);
            }

            Response<List<UserDto>> response = new Response<>(
                    true,"Get All user sucessfully",
                     userList.stream().map(user -> UserToUserMapper(user)).collect(Collectors.toList())
            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            System.out.println("Exception when GetAllUser" + e.getMessage());
            Response<List<User>>  response = new Response<>(
                    false,"Please try again when something wrong",null
            );
            return ResponseEntity.status(500).body(response);
        }
    }

//    @Async
//    public CompletableFuture<ResponseEntity<?>> getUserByEmail(String email){
//        try {
//
//        } catch (Exception e) {
//            System.out.println("Error when get user by email" + e.getMessage());
//        }
//    }
}
