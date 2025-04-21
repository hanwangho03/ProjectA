package com.example.ProjectA.controller;

import com.example.ProjectA.dto.Role.RoleCreate;
import com.example.ProjectA.dto.Role.RoleDto;
import com.example.ProjectA.dto.Role.RoleUpdate;
import com.example.ProjectA.iService.IServiceUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IServiceUser iServiceUser;

    @GetMapping
    public ResponseEntity<?> getAllRole(){

        return iServiceUser.getAllRole();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable int id) {
        return iServiceUser.getRoleById(id);
    }

    @PostMapping
    public ResponseEntity<?> createRole(@Valid @RequestBody RoleCreate role){
        return iServiceUser.createRole(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editRoleById(@Valid @RequestBody RoleUpdate role) {
        return iServiceUser.editRoleById(role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable int id) {

        return iServiceUser.deleteRole(id);
    }
}
