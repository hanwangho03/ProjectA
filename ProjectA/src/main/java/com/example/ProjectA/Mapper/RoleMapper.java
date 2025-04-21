package com.example.ProjectA.Mapper;

import com.example.ProjectA.dto.RoleDTO;
import com.example.ProjectA.entity.Role;

public class RoleMapper {

    public static RoleDTO RoleMapperToRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName((role.getName()));
        return roleDTO;
    }

    public static Role RoleDtoToRole(RoleDTO reDto){
        Role role = new Role();
        role.setName(role.getName());
        return role;
    }
}
