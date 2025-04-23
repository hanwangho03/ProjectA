package com.example.ProjectA.iService;

import com.example.ProjectA.dto.Permission.PermissionCreate;
import com.example.ProjectA.dto.Permission.PermissionUpdate;
import org.springframework.http.ResponseEntity;

public interface IServicePermission {
    ResponseEntity<?> getAllPermissions();
    ResponseEntity<?> getPermissionById(Long id);
    ResponseEntity<?> createPermission(PermissionCreate data);
    ResponseEntity<?> updatePermission(PermissionUpdate data);
    ResponseEntity<?> deletePermission(Long id);
}