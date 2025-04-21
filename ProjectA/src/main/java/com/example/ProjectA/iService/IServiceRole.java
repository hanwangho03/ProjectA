package com.example.ProjectA.iService;

import com.example.ProjectA.dto.Role.RoleDto;
import com.example.ProjectA.dto.Role.RoleUpdate;
import org.springframework.http.ResponseEntity;


public interface IServiceRole {
    ResponseEntity<?> getAllRole();
    ResponseEntity<?> getRoleById(int id);
    // bug
    ResponseEntity<?> createRole(RoleDto roleDto);
    // bug
    ResponseEntity<?> editRoleById(RoleUpdate RoleUpdate);
    ResponseEntity<?> deleteRole(int id);
}
