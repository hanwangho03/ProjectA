package com.example.ProjectA.service;

import com.example.ProjectA.Auth.AuthService;
import com.example.ProjectA.Helper.Response;
import com.example.ProjectA.Mapper.RoleMapper;
import com.example.ProjectA.dto.Role.RoleCreate;
import com.example.ProjectA.dto.Role.RoleDto;
import com.example.ProjectA.dto.Role.RoleUpdate;
import com.example.ProjectA.dto.User.UserDto;
import com.example.ProjectA.dto.User.UserLogin;
import com.example.ProjectA.dto.User.UserRegister;
import com.example.ProjectA.entity.Role;
import com.example.ProjectA.entity.User;
import com.example.ProjectA.iService.IServiceUser;
import com.example.ProjectA.repository.RoleRepository;
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

import static com.example.ProjectA.Mapper.RoleMapper.RoleMapperToRoleDto;
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
                System.out.println(user.getPassword());
                System.out.println(auth.hashPassword(data.getPassword()));
                if(auth.matchPassword(data.getPassword(),user.getPassword())){
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

            nUser.setRole(roleRepository.findByName("User").get());
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
    @Autowired
    private RoleRepository roleRepository;

    @Async
    public ResponseEntity<?> getAllRole(){
        try {
            List<Role> roleList = roleRepository.findAll();
            if(roleList.isEmpty()){
                System.out.println("Role is null");
                Response<?> response = new Response<>(false ,"Role is null",null);
                return ResponseEntity.status(404).body(response);
            }

            Response<List<RoleDto>> response = new Response<>(
                    true,"Get all role successfully",
                    roleList.stream().map(role -> RoleMapperToRoleDto(role)).collect(Collectors.toList()));
            return ResponseEntity.status(200).body(response);

        } catch (Exception e) {
            System.out.println("Exception when GetAllRoles:" + e.getMessage());
            Response<List<Role>> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null);
            return ResponseEntity.status(500).body(response);
        }

    }

    @Override
    public ResponseEntity<?> getRoleById(int id) {
        try {
            Optional<Role> roleOptional = roleRepository.findById(id);
            if (roleOptional.isEmpty()) {
                Response<RoleDto> response = new Response<>(false, "Role not found", null);
                return ResponseEntity.status(404).body(response);
            }

            Response<RoleDto> response = new Response<>(
                    true,
                    "Get role successfully",
                    RoleMapperToRoleDto(roleOptional.get())
            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            System.out.println("Exception when GetRoleById: " + e.getMessage());
            Response<RoleDto> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }

    @Async
    public ResponseEntity<?> createRole(RoleCreate data){
        try {
            if (roleRepository.existsByName(data.getName())) {
                Response<RoleDto> response = new Response<>(
                        false,
                        "Role name already exists",
                        null
                );
                return ResponseEntity.status(400).body(response);
            }

            Role role = new Role();
            role.setName(data.getName());
            Role savedRole = roleRepository.save(role);

            return ResponseEntity.status(201).body(new Response<>(
                    true,
                    "Role created successfully",
                    RoleMapper.RoleMapperToRoleDto(savedRole)
            ));
        } catch (Exception e) {
            System.out.println("Exception when create role: " + e.getMessage());
            Response<RoleDto> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }

    @Async
    public ResponseEntity<?>editRoleById(RoleUpdate data){
        int id = data.getId();
        try {
            Optional<Role> roleOptional = roleRepository.findById(id);
            if (roleOptional.isEmpty()) {
                Response<RoleDto> response = new Response<>(false, "Role not found", null);
                return ResponseEntity.status(404).body(response);
            }

            Role role = roleOptional.get();
            role.setName(data.getName());
            Role updatedRole = roleRepository.save(role);

            Response<RoleDto> response = new Response<>(
                    true,
                    "Role updated successfully",
                    RoleMapperToRoleDto(updatedRole)
            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            System.out.println("Exception when UpdateRole: " + e.getMessage());
            Response<RoleDto> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }

    @Async
    public ResponseEntity<?> deleteRole(int id) {
        try {
            Optional<Role> roleOptional = roleRepository.findById(id);
            if (roleOptional.isEmpty()) {
                Response<RoleDto> response = new Response<>(false, "Role not found", null);
                return ResponseEntity.status(404).body(response);
            }

            roleRepository.deleteById(id);
            Response<String> response = new Response<>(
                    true,
                    "Role deleted successfully",
                    null
            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            System.out.println("Exception when DeleteRole: " + e.getMessage());
            Response<RoleDto> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }
}
