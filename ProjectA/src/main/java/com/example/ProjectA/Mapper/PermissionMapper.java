package com.example.ProjectA.Mapper;

import com.example.ProjectA.dto.Permission.PermissionDto;
import com.example.ProjectA.entity.Permission;

public class PermissionMapper {
    public static PermissionDto PermissionMapperToPermissionDto(Permission permission) {
        PermissionDto permissionDto = new PermissionDto();
        permissionDto.setId(permission.getId());
        permissionDto.setName(permission.getName());
        permissionDto.setActions(permission.getActions());
        return permissionDto;
    }
}
