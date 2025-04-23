package com.example.ProjectA.controller;

import com.example.ProjectA.dto.Permission.PermissionCreate;
import com.example.ProjectA.dto.Permission.PermissionUpdate;
import com.example.ProjectA.iService.IServicePermission;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IServicePermission permissionService;

    @GetMapping
    public ResponseEntity<?> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPermissionById(@PathVariable Integer id) {
        return permissionService.getPermissionById(id);
    }

    @PostMapping
    public ResponseEntity<?> createPermission(@Valid @RequestBody PermissionCreate permissionDto) {
        return permissionService.createPermission(permissionDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePermission(@Valid @RequestBody PermissionUpdate permissionDto) {
        return permissionService.updatePermission(permissionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePermission(@PathVariable Integer id) {
        return permissionService.deletePermission(id);
    }
}
