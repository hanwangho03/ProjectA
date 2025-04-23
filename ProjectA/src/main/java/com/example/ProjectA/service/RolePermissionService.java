package com.example.ProjectA.service;

import com.example.ProjectA.Helper.Response;
import com.example.ProjectA.Mapper.RolePermissionMapper;
import com.example.ProjectA.dto.RolePermission.RolePermissionCreate;
import com.example.ProjectA.dto.RolePermission.RolePermissionDto;
import com.example.ProjectA.dto.RolePermission.RolePermissionUpdate;
import com.example.ProjectA.entity.Permission;
import com.example.ProjectA.entity.Role;
import com.example.ProjectA.entity.RolePermission;
import com.example.ProjectA.iService.IServiceRolePermission;
import com.example.ProjectA.repository.PermissionRepository;
import com.example.ProjectA.repository.RolePermissionRepository;
import com.example.ProjectA.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.ProjectA.Mapper.RolePermissionMapper.RolePermissionMapperToRolePermissionDto;

@Service
public class RolePermissionService implements IServiceRolePermission {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Async
    public ResponseEntity<?> getAllRolePermissions() {
        try {
            List<RolePermission> rolePermissionList = rolePermissionRepository.findAll();
            if (rolePermissionList.isEmpty()) {
                System.out.println("RolePermission is null");
                Response<List<RolePermissionDto>> response = new Response<>(false, "No role permissions found", null);
                return ResponseEntity.status(404).body(response);
            }

            Response<List<RolePermissionDto>> response = new Response<>(
                    true,
                    "Get all role permissions successfully",
                    rolePermissionList.stream().map(rolePermission -> RolePermissionMapperToRolePermissionDto(rolePermission)).collect(Collectors.toList())
            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            System.out.println("Exception when GetAllRolePermissions: " + e.getMessage());
            Response<List<RolePermissionDto>> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }

    @Async
    public ResponseEntity<?> getRolePermissionById(Long id) {
        try {
            Optional<RolePermission> rolePermissionOptional = rolePermissionRepository.findById(id);
            if (rolePermissionOptional.isEmpty()) {
                Response<RolePermissionDto> response = new Response<>(false, "Role permission not found", null);
                return ResponseEntity.status(404).body(response);
            }

            Response<RolePermissionDto> response = new Response<>(
                    true,
                    "Get role permission successfully",
                    RolePermissionMapper.RolePermissionMapperToRolePermissionDto(rolePermissionOptional.get())
            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            System.out.println("Exception when GetRolePermissionById: " + e.getMessage());
            Response<RolePermissionDto> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }


    public ResponseEntity<?> createRolePermission(RolePermissionCreate data) {
        try {
            Optional<Role> roleOptional = roleRepository.findById(data.getPermissionId());
            if (roleOptional.isEmpty()) {
                Response<RolePermissionDto> response = new Response<>(false, "Role not found", null);
                return ResponseEntity.status(404).body(response);
            }

            Optional<Permission> permissionOptional = permissionRepository.findById(data.getPermissionId());
            if (permissionOptional.isEmpty()) {
                Response<RolePermissionDto> response = new Response<>(false, "Permission not found", null);
                return ResponseEntity.status(404).body(response);
            }

            if (rolePermissionRepository.existsByRoleIdAndPermissionId(data.getPermissionId(), data.getRoleId())) {
                Response<RolePermissionDto> response = new Response<>(
                        false,
                        "Role already has this permission",
                        null
                );
                return ResponseEntity.status(400).body(response);
            }

            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermission(permissionOptional.get());
            rolePermission.setRole(roleOptional.get());
            rolePermission.setCreateDate(new Date());

            RolePermission savedRolePermission = rolePermissionRepository.save(rolePermission);

            Response<RolePermissionDto> response = new Response<>(
                    true,
                    "Role permission created successfully",
                    RolePermissionMapperToRolePermissionDto(savedRolePermission)
            );
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            System.out.println("Exception when create role permission: " + e.getMessage());
            Response<RolePermissionDto> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }


    public ResponseEntity<?> updateRolePermission(RolePermissionUpdate data) {
        Long id = data.getId().longValue();
        try {
            Optional<RolePermission> rolePermissionOptional = rolePermissionRepository.findById(id);
            if (rolePermissionOptional.isEmpty()) {
                Response<RolePermissionDto> response = new Response<>(false, "Role permission not found", null);
                return ResponseEntity.status(404).body(response);
            }

            Optional<Role> roleOptional = roleRepository.findById(data.getRoleId().getId());
            if (roleOptional.isEmpty()) {
                Response<RolePermissionDto> response = new Response<>(false, "Role not found", null);
                return ResponseEntity.status(404).body(response);
            }

            Optional<Permission> permissionOptional = permissionRepository.findById(data.getPermissionId().getId());
            if (permissionOptional.isEmpty()) {
                Response<RolePermissionDto> response = new Response<>(false, "Permission not found", null);
                return ResponseEntity.status(404).body(response);
            }

            RolePermission rolePermission = rolePermissionOptional.get();
            rolePermission.setPermission(data.getPermissionId());
            rolePermission.setRole(data.getRoleId());
            RolePermission updatedRolePermission = rolePermissionRepository.save(rolePermission);

            Response<RolePermissionDto> response = new Response<>(
                    true,
                    "Role permission updated successfully",
                    RolePermissionMapper.RolePermissionMapperToRolePermissionDto(updatedRolePermission)
            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            System.out.println("Exception when UpdateRolePermission: " + e.getMessage());
            Response<RolePermissionDto> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }

    public ResponseEntity<?> deleteRolePermission(Long id) {
        try {
            Optional<RolePermission> rolePermissionOptional = rolePermissionRepository.findById(id);
            if (rolePermissionOptional.isEmpty()) {
                Response<RolePermissionDto> response = new Response<>(false, "Role permission not found", null);
                return ResponseEntity.status(404).body(response);
            }

            rolePermissionRepository.deleteById(id);
            Response<String> response = new Response<>(
                    true,
                    "Role permission deleted successfully",
                    null
            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            System.out.println("Exception when DeleteRolePermission: " + e.getMessage());
            Response<RolePermissionDto> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }
}
