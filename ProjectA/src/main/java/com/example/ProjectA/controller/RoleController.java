package com.example.ProjectA.controller;

import com.example.ProjectA.dto.Role.RoleDto;
import com.example.ProjectA.dto.Role.RoleUpdate;
import com.example.ProjectA.iService.IServiceRole;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IServiceRole iServiceRole;

    @GetMapping
    public ResponseEntity<?> getAllRole(){

        return iServiceRole.getAllRole();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable int id) {
        return iServiceRole.getRoleById(id);
    }

    @PostMapping
    public ResponseEntity<?> createRole(@Valid @RequestBody RoleDto role){
        return iServiceRole.createRole(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editRoleById(@Valid @RequestBody RoleUpdate role) {
        return iServiceRole.editRoleById(role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable int id) {

        return iServiceRole.deleteRole(id);
    }
}
