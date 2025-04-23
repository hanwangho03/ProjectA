package com.example.ProjectA.repository;

import com.example.ProjectA.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    boolean existsByRoleIdAndPermissionId(Long roleId, Long permissionId);
}