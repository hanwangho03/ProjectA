package com.example.ProjectA.controller;

import com.example.ProjectA.dto.RolePermission.RolePermissionCreate;
import com.example.ProjectA.dto.RolePermission.RolePermissionUpdate;
import com.example.ProjectA.iService.IServiceRolePermission;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rolepermission")
public class RolePrermissionController {
    @Autowired
    private IServiceRolePermission rolePermissionService;

    @GetMapping
    public ResponseEntity<?> getAllRolePermission(){
        return rolePermissionService.getAllRolePermissions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRolePermissionById(@PathVariable Long id){
        return rolePermissionService.getRolePermissionById(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createRolePermission(@Valid @RequestBody RolePermissionCreate rolePermissionCreate){
        return rolePermissionService.createRolePermission(rolePermissionCreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRolePermission(@Valid @RequestBody RolePermissionUpdate rolePermissionUpdate){
        return rolePermissionService.updateRolePermission(rolePermissionUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRolePermission(@PathVariable Long id){
        return rolePermissionService.deleteRolePermission(id);
    }
}
