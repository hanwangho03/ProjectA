package com.example.ProjectA.dto.RolePermission;

import com.example.ProjectA.entity.Permission;
import com.example.ProjectA.entity.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class RolePermissionCreate {

    @NotNull(message = "Permission ID không được để trống")
    private Long permissionId;

    @NotNull(message = "Role ID không được để trống")
    private Long roleId;


}
