package com.example.ProjectA.controller;

import com.example.ProjectA.dto.Supplier.SupplierDto;
import com.example.ProjectA.dto.WarehouseReceipt.WarehouseReceiptCreate;
import com.example.ProjectA.dto.WarehouseReceipt.WarehouseReceiptUpdate;
import com.example.ProjectA.iService.iServiceWarehouseReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warehouse-receipts")
public class WarehouseReceiptController {
    @Autowired
    private iServiceWarehouseReceipt service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody WarehouseReceiptCreate dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody WarehouseReceiptUpdate dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
