package com.example.ProjectA.service;

import com.example.ProjectA.Helper.Response;
import com.example.ProjectA.Mapper.RoleMapper;
import com.example.ProjectA.dto.RoleDTO;
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

import static com.example.ProjectA.Mapper.RoleMapper.RoleMapperToRoleDTO;

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
                Response<List<Role>> response = new Response<>();
                return ResponseEntity.status(404).body(response);
            }

            Response<List<RoleDTO>> response = new Response<>(
                    true,"Get all role successfull",
                    roleList.stream().map(role -> RoleMapperToRoleDTO(role)).collect(Collectors.toList()));
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

    @Async
    public ResponseEntity<?> getRoleById(Integer id) {
        try {
            Optional<Role> roleOptional = roleRepository.findById(id);
            if (roleOptional.isEmpty()) {
                Response<RoleDTO> response = new Response<>(false, "Role not found", null);
                return ResponseEntity.status(404).body(response);
            }

            Response<RoleDTO> response = new Response<>(
                    true,
                    "Get role successfully",
                    RoleMapperToRoleDTO(roleOptional.get())
            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            System.out.println("Exception when GetRoleById: " + e.getMessage());
            Response<RoleDTO> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }

    @Async
    public ResponseEntity<?> createRole(RoleDTO roleDTO){
        try {
            if (roleRepository.existsByName(roleDTO.getName())) {
                Response<RoleDTO> response = new Response<>(
                        false,
                        "Role name already exists",
                        null
                );
                return ResponseEntity.status(400).body(response);
            }

            Role role = new Role();
            role.setName(roleDTO.getName());
            Role savedRole = roleRepository.save(role);

            return ResponseEntity.status(201).body(new Response<>(
                    true,
                    "Role created successfully",
                    RoleMapper.RoleMapperToRoleDTO(savedRole)
            ));
        } catch (Exception e) {
            System.out.println("Exception when create role: " + e.getMessage());
            Response<RoleDTO> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }

    @Async
    public ResponseEntity<?>editRoleById(RoleDTO roleDTO){
        int id = roleDTO.getId();
        try {
            Optional<Role> roleOptional = roleRepository.findById(id);
            if (roleOptional.isEmpty()) {
                Response<RoleDTO> response = new Response<>(false, "Role not found", null);
                return ResponseEntity.status(404).body(response);
            }

            Role role = roleOptional.get();
            role.setName(roleDTO.getName());
            Role updatedRole = roleRepository.save(role);

            Response<RoleDTO> response = new Response<>(
                    true,
                    "Role updated successfully",
                    RoleMapperToRoleDTO(updatedRole)
            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            System.out.println("Exception when UpdateRole: " + e.getMessage());
            Response<RoleDTO> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }

    @Async
    public ResponseEntity<?> deleteRole(Integer id) {
        try {
            Optional<Role> roleOptional = roleRepository.findById(id);
            if (roleOptional.isEmpty()) {
                Response<RoleDTO> response = new Response<>(false, "Role not found", null);
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
            Response<RoleDTO> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }
}
