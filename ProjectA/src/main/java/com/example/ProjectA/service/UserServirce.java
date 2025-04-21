package com.example.ProjectA.service;

import com.example.ProjectA.Auth.AuthService;
import com.example.ProjectA.Helper.Response;
import com.example.ProjectA.dto.User.UserDto;
import com.example.ProjectA.dto.User.UserLogin;
import com.example.ProjectA.dto.User.UserRegister;
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
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.ProjectA.Mapper.UserMapper.UserMapperToUserDto;



@Service
public class UserServirce implements IServiceUser {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService auth;

    private BloomFilter<String> RBloomFilter;

    // sẽ chạy khi khời tạo dữ án
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
                     userList.stream().map(user -> UserMapperToUserDto(user)).collect(Collectors.toList())
            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            System.out.println("Exception when GetAllUser :" + e.getMessage());
            Response<List<User>>  response = new Response<>(
                    false,"Please try again when something wrong",null
            );
            return ResponseEntity.status(500).body(response);
        }
    }

    @Async
    public ResponseEntity<?> Login(UserLogin data){
        try {

            if(!RBloomFilter.mightContain(data.getEmail())){
                Response<?> response = new Response<>(
                        false,"Not found user",null
                );
                return ResponseEntity.status(404).body(response);
            }

            Optional<User> rUser =  userRepository.findByEmail(data.getEmail());
            if(rUser.isPresent()) {
                User user = rUser.get();
                if(data.getPassword().equals(auth.matchPassword(user.getPassword()))){
                    String token = auth.generateToken(user.getEmail());
                    Response<String> response = new Response<>(true,"Login successfully",token);
                    return ResponseEntity.status(200).body(response);
                }
                Response<String> response = new Response<>(false,"Login failed",null);
                return ResponseEntity.status(200).body(response);
            }

            Response<?> response = new Response<>(
                    false,"Not found user",null
            );
            return ResponseEntity.status(404).body(response);
        } catch (Exception e) {
            System.out.println("Exception when Login" + e.getMessage());
            Response<?>  response = new Response<>(
                    false,"Please try again when something wrong",null
            );
            return ResponseEntity.status(500).body(response);
        }
    }

    public  ResponseEntity<?> Register(UserRegister data){
        try {

            if(RBloomFilter.mightContain(data.getEmail())){
                Response<?> response = new Response<>(
                        false,"User exist",null
                );
                return ResponseEntity.status(200).body(response);
            }
            User nUser = new User();
            nUser.setName(data.getName());
            nUser.setEmail(data.getEmail());
            nUser.setPassword(auth.hashPassword(data.getPassword()));
            userRepository.save(nUser);
            Response<UserDto>  response = new Response<>(true,"Register successfully",UserMapperToUserDto(nUser));
            RBloomFilter.put(nUser.getEmail());
            return  ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            System.out.println("Exception when Register" + e.getMessage());
            Response<?>  response = new Response<>(
                    false,"Please try again when something wrong",null
            );
            return ResponseEntity.status(500).body(response);
        }
    }
}
