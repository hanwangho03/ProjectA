package com.example.ProjectA.controller;

import com.example.ProjectA.dto.RoleDTO;
import com.example.ProjectA.dto.StatusDto;
import com.example.ProjectA.iService.IServiceRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
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
    public ResponseEntity<?> createRole(){
        return iServiceRole.createRole();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editRoleById(@RequestBody RoleDTO roleDTO) {
        return iServiceRole.editRoleById(roleDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable int id) {
        return iServiceRole.deleteRole(id);
    }
}
