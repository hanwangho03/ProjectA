package com.example.ProjectA.iService;

import com.example.ProjectA.dto.WarehouseReceipt.WarehouseReceiptCreate;
import com.example.ProjectA.dto.WarehouseReceipt.WarehouseReceiptUpdate;
import org.springframework.http.ResponseEntity;
public interface iServiceWarehouseReceipt {
    ResponseEntity<?> getAll();
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> create(WarehouseReceiptCreate dto);
    ResponseEntity<?> update(WarehouseReceiptUpdate dto);
    ResponseEntity<?> delete(Long id);
}

