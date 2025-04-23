package com.example.ProjectA.iService;

import com.example.ProjectA.dto.RolePermission.RolePermissionCreate;
import com.example.ProjectA.dto.RolePermission.RolePermissionUpdate;
import org.springframework.http.ResponseEntity;

public interface IServiceRolePermission {
    ResponseEntity<?> getAllRolePermissions();
    ResponseEntity<?> getRolePermissionById(Long id);
    ResponseEntity<?> createRolePermission(RolePermissionCreate data);
    ResponseEntity<?> updateRolePermission(RolePermissionUpdate data);
    ResponseEntity<?> deleteRolePermission(Long id);
}
