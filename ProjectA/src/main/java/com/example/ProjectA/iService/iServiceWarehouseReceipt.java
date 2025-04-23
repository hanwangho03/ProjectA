package com.example.ProjectA.iService;

import com.example.ProjectA.dto.WarehouseReceipt.WarehouseReceiptCreate;
import org.springframework.http.ResponseEntity;
public interface iServiceWarehouseReceipt {
    ResponseEntity<?> getAll();
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> create(WarehouseReceiptCreate dto);
    ResponseEntity<?> delete(Long id);
}

