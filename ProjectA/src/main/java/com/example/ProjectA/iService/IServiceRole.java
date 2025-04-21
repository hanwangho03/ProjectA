package com.example.ProjectA.iService;

import com.example.ProjectA.dto.RoleDTO;
import org.springframework.http.ResponseEntity;

public interface IServiceRole {
    ResponseEntity<?> getAllRole();
    ResponseEntity<?> getRoleById(int id);
    ResponseEntity<?> createRole();
    ResponseEntity<?> editRoleById(RoleDTO roleDTO);
    ResponseEntity<?> deleteRole(int id);
}
