package com.example.ProjectA.iService;

import com.example.ProjectA.dto.Role.RoleCreate;
import com.example.ProjectA.dto.Role.RoleDto;
import com.example.ProjectA.dto.Role.RoleUpdate;
import com.example.ProjectA.dto.User.UserLogin;
import com.example.ProjectA.dto.User.UserRegister;
import org.springframework.http.ResponseEntity;


public interface IServiceUser {
     // ResponseEntity<?> sẽ trả về 1 object
     ResponseEntity<?> getAllUser();
     ResponseEntity<?> Login(UserLogin user);
     ResponseEntity<?> Register(UserRegister user);
     ResponseEntity<?> getAllRole();
     ResponseEntity<?> getRoleById(int id);
     // bug
     ResponseEntity<?> createRole(RoleCreate role);
     // bug
     ResponseEntity<?> editRoleById(RoleUpdate RoleUpdate);
     ResponseEntity<?> deleteRole(int id);
}
