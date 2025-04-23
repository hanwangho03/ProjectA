package com.example.ProjectA.service;

import com.example.ProjectA.Helper.Response;
import com.example.ProjectA.Mapper.SupplierMapper;
import com.example.ProjectA.Mapper.iServiceSupplier;
import com.example.ProjectA.dto.Supplier.SupplierCreate;
import com.example.ProjectA.dto.Supplier.SupplierDto;
import com.example.ProjectA.entity.Supplier;
import com.example.ProjectA.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplierService implements iServiceSupplier {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public ResponseEntity<?> getAll() {
        List<Supplier> list = supplierRepository.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.status(404).body(new Response<>(false, "No suppliers found", null));
        }
        List<SupplierDto> dtoList = list.stream().map(SupplierMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(new Response<>(true, "Retrieved all suppliers successfully", dtoList));
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        Optional<Supplier> optional = supplierRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(404).body(new Response<>(false, "Supplier not found", null));
        }
        return ResponseEntity.ok(new Response<>(true, "Supplier found", SupplierMapper.toDto(optional.get())));
    }

    @Override
    public ResponseEntity<?> create(SupplierCreate dto) {
        Supplier supplier = SupplierMapper.toEntity(dto);
        Supplier saved = supplierRepository.save(supplier);
        return ResponseEntity.status(201).body(new Response<>(true, "Supplier created successfully", SupplierMapper.toDto(saved)));
    }

    @Override
    public ResponseEntity<?> update(Long id, SupplierDto dto) {
        Optional<Supplier> optional = supplierRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(404).body(new Response<>(false, "Supplier not found", null));
        }

        Supplier supplier = optional.get();
        supplier.setName(dto.getName());
        supplier.setAddress(dto.getAddress());
        supplier.setPhone(dto.getPhone());

        Supplier updated = supplierRepository.save(supplier);
        return ResponseEntity.ok(new Response<>(true, "Supplier updated successfully", SupplierMapper.toDto(updated)));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (!supplierRepository.existsById(id)) {
            return ResponseEntity.status(404).body(new Response<>(false, "Supplier not found", null));
        }
        supplierRepository.deleteById(id);
        return ResponseEntity.ok(new Response<>(true, "Supplier deleted successfully", null));
    }
}
