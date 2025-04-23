package com.example.ProjectA.service;

import com.example.ProjectA.Helper.Response;
import com.example.ProjectA.Mapper.PermissionMapper;
import com.example.ProjectA.dto.Permission.PermissionCreate;
import com.example.ProjectA.dto.Permission.PermissionDto;
import com.example.ProjectA.dto.Permission.PermissionUpdate;
import com.example.ProjectA.entity.Permission;
import com.example.ProjectA.iService.IServicePermission;
import com.example.ProjectA.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.ProjectA.Mapper.PermissionMapper.PermissionMapperToPermissionDto;

@Service
public class PermissionService implements IServicePermission {
    @Autowired
    private PermissionRepository permissionRepository;

    @Async
    public ResponseEntity<?> getAllPermissions() {
        try {
            List<Permission> permissionList = permissionRepository.findAll();
            if (permissionList.isEmpty()) {
                System.out.println("Permission is null");
                Response<List<PermissionDto>> response = new Response<>(false, "No permissions found", null);
                return ResponseEntity.status(404).body(response);
            }

            Response<List<PermissionDto>> response = new Response<>(
                    true,
                    "Get all permissions successfully",
                    permissionList.stream().map(permission -> PermissionMapperToPermissionDto(permission)).collect(Collectors.toList())
            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            System.out.println("Exception when GetAllPermissions: " + e.getMessage());
            Response<List<PermissionDto>> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }

    public ResponseEntity<?> getPermissionById(int id) {
        try {
            Optional<Permission> permissionOptional = permissionRepository.findById(id);
            if (permissionOptional.isEmpty()) {
                Response<PermissionDto> response = new Response<>(false, "Permission not found", null);
                return ResponseEntity.status(404).body(response);
            }

            Response<PermissionDto> response = new Response<>(
                    true,
                    "Get permission successfully",
                    PermissionMapper.PermissionMapperToPermissionDto(permissionOptional.get())
            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            System.out.println("Exception when GetPermissionById: " + e.getMessage());
            Response<PermissionDto> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }

    @Async
    public ResponseEntity<?> createPermission(PermissionCreate data) {
        try {
            if (permissionRepository.existsByName(data.getName())) {
                Response<PermissionDto> response = new Response<>(
                        false,
                        "Permission name already exists",
                        null
                );
                return ResponseEntity.status(400).body(response);
            }

            Permission permission = new Permission();
            permission.setName(data.getName());
            permission.setActions(data.getActions());
            Permission savedPermission = permissionRepository.save(permission);

            Response<PermissionDto> response = new Response<>(
                    true,
                    "Permission created successfully",
                    PermissionMapper.PermissionMapperToPermissionDto(savedPermission)
            );
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            System.out.println("Exception when create permission: " + e.getMessage());
            Response<PermissionDto> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }

    @Async
    public ResponseEntity<?> updatePermission(PermissionUpdate data) {
        int id = data.getId();
        try {
            Optional<Permission> permissionOptional = permissionRepository.findById(id);
            if (permissionOptional.isEmpty()) {
                Response<PermissionDto> response = new Response<>(false, "Permission not found", null);
                return ResponseEntity.status(404).body(response);
            }

            Permission permission = permissionOptional.get();
            permission.setName(data.getName());
            permission.setActions(data.getActions());
            Permission updatedPermission = permissionRepository.save(permission);

            Response<PermissionDto> response = new Response<>(
                    true,
                    "Permission updated successfully",
                    PermissionMapper.PermissionMapperToPermissionDto(updatedPermission)
            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            System.out.println("Exception when UpdatePermission: " + e.getMessage());
            Response<PermissionDto> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }

    @Async
    public ResponseEntity<?> deletePermission(int id) {
        try {
            Optional<Permission> permissionOptional = permissionRepository.findById(id);
            if (permissionOptional.isEmpty()) {
                Response<PermissionDto> response = new Response<>(false, "Permission not found", null);
                return ResponseEntity.status(404).body(response);
            }

            permissionRepository.deleteById(id);
            Response<String> response = new Response<>(
                    true,
                    "Permission deleted successfully",
                    null
            );
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            System.out.println("Exception when DeletePermission: " + e.getMessage());
            Response<PermissionDto> response = new Response<>(
                    false,
                    "Please try again when something went wrong",
                    null
            );
            return ResponseEntity.status(500).body(response);
        }
    }
}
