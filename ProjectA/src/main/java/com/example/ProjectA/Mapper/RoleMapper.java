package com.example.ProjectA.Mapper;

import com.example.ProjectA.dto.Role.RoleDto;
import com.example.ProjectA.entity.Role;

public class RoleMapper {

    public static RoleDto RoleMapperToRoleDto(Role role) {
        RoleDto roleDTO = new RoleDto();
        roleDTO.setId(role.getId());
        roleDTO.setName((role.getName()));
        return roleDTO;
    }

    public static Role RoleDtoToRole(RoleDto reDto){
        Role role = new Role();
        role.setName(role.getName());
        return role;
    }
}
