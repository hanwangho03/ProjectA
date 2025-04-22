package com.example.ProjectA.iService;


import com.example.ProjectA.dto.Product.ProductCreate;
import com.example.ProjectA.dto.Product.ProductUpdate;
import com.example.ProjectA.dto.TypeOfProduct.TypeOfProductCreate;
import com.example.ProjectA.dto.TypeOfProduct.TypeOfProductDto;
import com.example.ProjectA.entity.TypeOfProduct;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface iServiceProduct {
    ResponseEntity<?> getAll();
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> create(TypeOfProductCreate dto);
    ResponseEntity<?> update(Long id, TypeOfProductDto dto);
    ResponseEntity<?> delete(Long id);
    ResponseEntity<?> getAllProducts();
    ResponseEntity<?> getProductById(Long id);
    ResponseEntity<?> createProduct(ProductCreate dto);
    ResponseEntity<?> updateProduct(ProductUpdate dto);
    ResponseEntity<?> deleteProduct(Long id);

}
