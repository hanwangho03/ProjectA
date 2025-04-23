package com.example.ProjectA.controller;


import com.example.ProjectA.dto.Product.ProductCreate;
import com.example.ProjectA.dto.Product.ProductUpdate;
import com.example.ProjectA.iService.iServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    private iServiceProduct productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductCreate dto) {
        return productService.createProduct(dto);
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody ProductUpdate dto) {
        return productService.updateProduct(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

}
