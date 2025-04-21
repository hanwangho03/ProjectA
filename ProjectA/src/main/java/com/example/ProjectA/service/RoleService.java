package com.example.ProjectA.service;

import com.example.ProjectA.Helper.Response;
import com.example.ProjectA.Mapper.RoleMapper;
import com.example.ProjectA.dto.Role.RoleDto;
import com.example.ProjectA.dto.Role.RoleUpdate;
import com.example.ProjectA.entity.Role;
import com.example.ProjectA.iService.IServiceRole;
import com.example.ProjectA.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.ProjectA.Mapper.RoleMapper.RoleMapperToRoleDto;

@Service
public class RoleService implements IServiceRole {

    @Autowired
    private RoleRepository roleRepository;

    @Async
    public ResponseEntity<?> getAllRole(){
        try {
            List<Role> roleList = roleRepository.findAll();
            if(roleList.isEmpty()){
                System.out.println("Role is null");
                Response<?> response = new Response<>(false ,"Role is null",null);
                return ResponseEntity.status(404).body(response);
            }

            Response<List<RoleDto>> response = new Response<>(
                    true,"Get all role successfully",
                    roleList.stream().map(role -> RoleMapperToRoleDto(role)).collect(Collectors.toList()));
            return ResponseEntity.status(200).body(response);

        } catch (Exception e) {
            System.out.println("Exception when GetAllRoles:" + e.getMessage());
            Response<List<Role>> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null);
            return ResponseEntity.status(500).body(response);
        }

    }

    @Override
    public ResponseEntity<?> getRoleById(int id) {
        try {
            Optional<Role> roleOptional = roleRepository.findById(id);
            if (roleOptional.isEmpty()) {
                Response<RoleDto> response = new Response<>(false, "Role not found", null);
                return ResponseEntity.status(404).body(response);
            }

            Response<RoleDto> response = new Response<>(
                    true,
                    "Get role successfully",
                    RoleMapperToRoleDto(roleOptional.get())
            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            System.out.println("Exception when GetRoleById: " + e.getMessage());
            Response<RoleDto> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }

    @Async
    public ResponseEntity<?> createRole(RoleDto RoleDto){
        try {
            if (roleRepository.existsByName(RoleDto.getName())) {
                Response<RoleDto> response = new Response<>(
                        false,
                        "Role name already exists",
                        null
                );
                return ResponseEntity.status(400).body(response);
            }

            Role role = new Role();
            role.setName(RoleDto.getName());
            Role savedRole = roleRepository.save(role);

            return ResponseEntity.status(201).body(new Response<>(
                    true,
                    "Role created successfully",
                    RoleMapper.RoleMapperToRoleDto(savedRole)
            ));
        } catch (Exception e) {
            System.out.println("Exception when create role: " + e.getMessage());
            Response<RoleDto> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }

    @Async
    public ResponseEntity<?>editRoleById(RoleUpdate data){
        int id = data.getId();
        try {
            Optional<Role> roleOptional = roleRepository.findById(id);
            if (roleOptional.isEmpty()) {
                Response<RoleDto> response = new Response<>(false, "Role not found", null);
                return ResponseEntity.status(404).body(response);
            }

            Role role = roleOptional.get();
            role.setName(data.getName());
            Role updatedRole = roleRepository.save(role);

            Response<RoleDto> response = new Response<>(
                    true,
                    "Role updated successfully",
                    RoleMapperToRoleDto(updatedRole)
            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            System.out.println("Exception when UpdateRole: " + e.getMessage());
            Response<RoleDto> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }

    @Async
    public ResponseEntity<?> deleteRole(int id) {
        try {
            Optional<Role> roleOptional = roleRepository.findById(id);
            if (roleOptional.isEmpty()) {
                Response<RoleDto> response = new Response<>(false, "Role not found", null);
                return ResponseEntity.status(404).body(response);
            }

            roleRepository.deleteById(id);
            Response<String> response = new Response<>(
                    true,
                    "Role deleted successfully",
                    null
            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            System.out.println("Exception when DeleteRole: " + e.getMessage());
            Response<RoleDto> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }
}
