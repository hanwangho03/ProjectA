package com.example.ProjectA.controller;

import com.example.ProjectA.dto.TypeOfProduct.TypeOfProductCreate;
import com.example.ProjectA.dto.TypeOfProduct.TypeOfProductDto;
import com.example.ProjectA.iService.iServiceProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/typeofproduct")
@RequiredArgsConstructor
public class TypeOfProductController {

    private final iServiceProduct typeOfProductService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return typeOfProductService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return typeOfProductService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TypeOfProductCreate dto) {
        return typeOfProductService.create(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody TypeOfProductDto dto) {
        return typeOfProductService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return typeOfProductService.delete(id);
    }
}
