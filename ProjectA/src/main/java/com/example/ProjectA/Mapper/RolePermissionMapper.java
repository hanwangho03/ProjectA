package com.example.ProjectA.Mapper;

import com.example.ProjectA.dto.RolePermission.RolePermissionDto;
import com.example.ProjectA.entity.RolePermission;

public class RolePermissionMapper {

    public static RolePermissionDto RolePermissionMapperToRolePermissionDto(RolePermission data){
        RolePermissionDto rolePermissionDto = new RolePermissionDto();
        rolePermissionDto.setId(data.getId());
        rolePermissionDto.setRoleId(data.getRole().getId());
        rolePermissionDto.setPermissionId(data.getPermission().getId());
        rolePermissionDto.setCreateDate(data.getCreateDate());
        return rolePermissionDto;
    }
}
