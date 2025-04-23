package com.example.ProjectA.service;

import com.example.ProjectA.Helper.Response;
import com.example.ProjectA.Mapper.WarehouseReceiptMapper;
import com.example.ProjectA.dto.WarehouseReceipt.WarehouseReceiptCreate;
import com.example.ProjectA.dto.WarehouseReceipt.WarehouseReceiptUpdate;
import com.example.ProjectA.entity.Status;
import com.example.ProjectA.entity.User;
import com.example.ProjectA.entity.WarehouseReceipt;
import com.example.ProjectA.iService.iServiceWarehouseReceipt;
import com.example.ProjectA.repository.StatusRepository;
import com.example.ProjectA.repository.UserRepository;
import com.example.ProjectA.repository.WarehouseReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WarehouseReceiptService implements iServiceWarehouseReceipt {

    @Autowired
    private WarehouseReceiptRepository warehouseReceiptRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public ResponseEntity<?> getAll() {
        List<WarehouseReceipt> list = warehouseReceiptRepository.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.status(404).body(new Response<>(false, "No warehouse receipts found", null));
        }
        return ResponseEntity.ok(new Response<>(true, "Success", list.stream().map(WarehouseReceiptMapper::WarehouseReceiptToMapper).collect(Collectors.toList())));
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        Optional<WarehouseReceipt> optional = warehouseReceiptRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(404).body(new Response<>(false, "Not found", null));
        }
        return ResponseEntity.ok(new Response<>(true, "Found", WarehouseReceiptMapper.WarehouseReceiptToMapper(optional.get())));
    }

    @Override
    public ResponseEntity<?> create(WarehouseReceiptCreate dto) {
        try {
            WarehouseReceipt entity = new WarehouseReceipt();
            entity.setTotal(dto.getTotal());

            if (dto.getUserId() != null) {
                Optional<User> user = userRepository.findById(dto.getUserId());
                user.ifPresent(entity::setUser);
            }

            Optional<Status> status = statusRepository.findById(dto.getStatusId());
            if (status.isEmpty()) {
                return ResponseEntity.status(400).body(new Response<>(false, "Invalid status", null));
            }
            entity.setStatus(status.get());

            WarehouseReceipt saved = warehouseReceiptRepository.save(entity);
            return ResponseEntity.status(201).body(new Response<>(true, "Created successfully", WarehouseReceiptMapper.WarehouseReceiptToMapper(saved)));
        } catch (Exception e) {
            System.out.println("Error create: " + e.getMessage());
            return ResponseEntity.status(500).body(new Response<>(false, "Internal server error", null));
        }
    }

    @Override
    public ResponseEntity<?> update(WarehouseReceiptUpdate dto) {
        try {
            Optional<WarehouseReceipt> optional = warehouseReceiptRepository.findById(dto.getId());
            if (optional.isEmpty()) {
                return ResponseEntity.status(404).body(new Response<>(false, "Warehouse receipt not found", null));
            }

            WarehouseReceipt entity = optional.get();
            if (dto.getTotal() != null) {
                entity.setTotal(dto.getTotal());
            }
            if (dto.getStatusId() != null) {
                Optional<Status> status = statusRepository.findById(dto.getStatusId());
                if (status.isEmpty()) {
                    return ResponseEntity.status(400).body(new Response<>(false, "Invalid status", null));
                }
                entity.setStatus(status.get());
            }
            WarehouseReceipt updated = warehouseReceiptRepository.save(entity);
            return ResponseEntity.ok(new Response<>(true, "Updated successfully", WarehouseReceiptMapper.WarehouseReceiptToMapper(updated)));
        } catch (Exception e) {
            System.out.println("Error update: " + e.getMessage());
            return ResponseEntity.status(500).body(new Response<>(false, "Internal server error", null));
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<WarehouseReceipt> optional = warehouseReceiptRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(404).body(new Response<>(false, "Not found", null));
        }
        warehouseReceiptRepository.deleteById(id);
        return ResponseEntity.ok(new Response<>(true, "Deleted successfully", null));
    }
}