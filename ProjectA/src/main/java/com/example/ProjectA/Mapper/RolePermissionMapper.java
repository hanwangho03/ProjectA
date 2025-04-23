package com.example.ProjectA.Mapper;

import com.example.ProjectA.dto.RolePermission.RolePermissionDto;
import com.example.ProjectA.entity.RolePermission;

public class RolePermissionMapper {

    public static RolePermissionDto RolePermissionMapperToRolePermissionDto(RolePermission data){
        RolePermissionDto rolePermissionDto = new RolePermissionDto();
        rolePermissionDto.setId(data.getId());
        rolePermissionDto.setRole(data.getRole());
        rolePermissionDto.setPermission(data.getPermission());
        rolePermissionDto.setCreateDate(data.getCreateDate());
        return rolePermissionDto;
    }
}
