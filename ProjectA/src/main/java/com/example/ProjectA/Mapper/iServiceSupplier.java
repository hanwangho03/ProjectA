package com.example.ProjectA.Mapper;

import com.example.ProjectA.dto.Supplier.SupplierCreate;
import com.example.ProjectA.dto.Supplier.SupplierDto;
import org.springframework.http.ResponseEntity;

public interface iServiceSupplier {
    ResponseEntity<?> getAll();
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> create(SupplierCreate dto);
    ResponseEntity<?> update(Long id, SupplierDto dto);
    ResponseEntity<?> delete(Long id);
}