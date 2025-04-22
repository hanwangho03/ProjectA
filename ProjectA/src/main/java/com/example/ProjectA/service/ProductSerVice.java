package com.example.ProjectA.service;

import com.example.ProjectA.Helper.Response;
import com.example.ProjectA.Mapper.ProductMapper;
import com.example.ProjectA.Mapper.TypeOfProductMapper;
import com.example.ProjectA.dto.Product.ProductCreate;
import com.example.ProjectA.dto.Product.ProductDto;
import com.example.ProjectA.dto.Product.ProductUpdate;
import com.example.ProjectA.dto.TypeOfProduct.TypeOfProductCreate;
import com.example.ProjectA.dto.TypeOfProduct.TypeOfProductDto;
import com.example.ProjectA.entity.Product;
import com.example.ProjectA.entity.TypeOfProduct;
import com.example.ProjectA.iService.iServiceProduct;
import com.example.ProjectA.repository.ProductRepository;
import com.example.ProjectA.repository.TypeOfProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductSerVice implements iServiceProduct {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TypeOfProductRepository typeOfProductRepository;

    @Override
    public ResponseEntity<?> getAll() {
        try {
            List<TypeOfProduct> list = typeOfProductRepository.findAll();
            if (list.isEmpty()) {
                return ResponseEntity.status(404).body(new Response<>(false, "No type of product found", null));
            }
            List<TypeOfProduct> dtoList = list.stream()
                    .map(TypeOfProductMapper::TypeOfProductToMapper)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new Response<>(true, "Retrieved all type of products successfully", dtoList));
        } catch (Exception e) {
            System.out.println("Error when getAll: " + e.getMessage());
            return ResponseEntity.status(500).body(new Response<>(false, "An error occurred while retrieving type of products", null));
        }
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        try {
            Optional<TypeOfProduct> optional = typeOfProductRepository.findById(id);
            if (optional.isPresent()) {
                return ResponseEntity.ok(new Response<>(true, "Type of product found", TypeOfProductMapper.TypeOfProductToMapper(optional.get())));
            }
            return ResponseEntity.status(404).body(new Response<>(false, "Type of product not found", null));
        } catch (Exception e) {
            System.out.println("Error when getById: " + e.getMessage());
            return ResponseEntity.status(500).body(new Response<>(false, "An error occurred while retrieving type of product", null));
        }
    }

    @Override
    public ResponseEntity<?> create(TypeOfProductCreate dto) {
        try {
            boolean exists = typeOfProductRepository.findByName(dto.getName()).isPresent();
            if (exists) {
                return ResponseEntity.status(400).body(new Response<>(false, "Type of product with this name already exists", null));
            }
            TypeOfProduct entity = new TypeOfProduct();
            entity.setName(dto.getName());
            entity.setStorageConditions(dto.getStorageConditions());
            entity.setRequiredTemperature(dto.getRequiredTemperature());
            TypeOfProduct saved = typeOfProductRepository.save(entity);
            return ResponseEntity.status(201).body(new Response<>(true, "Type of product created successfully", TypeOfProductMapper.TypeOfProductToMapper(saved)));
        } catch (Exception e) {
            System.out.println("Error when create: " + e.getMessage());
            return ResponseEntity.status(500).body(new Response<>(false, "An error occurred while creating type of product", null));
        }
    }

    @Override
    public ResponseEntity<?> update(Long id, TypeOfProductDto dto) {
        try {
            Optional<TypeOfProduct> optional = typeOfProductRepository.findById(id);
            if (optional.isEmpty()) {
                return ResponseEntity.status(404).body(new Response<>(false, "Type of product not found", null));
            }

            Optional<TypeOfProduct> existingByName = typeOfProductRepository.findByName(dto.getName());
            if (existingByName.isPresent() && existingByName.get().getId() != id) {
                return ResponseEntity.status(400).body(new Response<>(false, "Another type of product with this name already exists", null));
            }

            TypeOfProduct entity = optional.get();
            entity.setName(dto.getName());
            entity.setRequiredTemperature(dto.getRequiredTemperature());
            entity.setStorageConditions(dto.getStorageConditions());

            TypeOfProduct updated = typeOfProductRepository.save(entity);
            return ResponseEntity.ok(new Response<>(true, "Type of product updated successfully", TypeOfProductMapper.TypeOfProductToMapper(updated)));
        } catch (Exception e) {
            System.out.println("Error when update: " + e.getMessage());
            return ResponseEntity.status(500).body(new Response<>(false, "An error occurred while updating type of product", null));
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        try {
            Optional<TypeOfProduct> optional = typeOfProductRepository.findById(id);
            if (optional.isEmpty()) {
                return ResponseEntity.status(404).body(new Response<>(false, "Type of product not found", null));
            }
            typeOfProductRepository.deleteById(id);
            return ResponseEntity.ok(new Response<>(true, "Type of product deleted successfully", null));
        } catch (Exception e) {
            System.out.println("Error when delete: " + e.getMessage());
            return ResponseEntity.status(500).body(new Response<>(false, "An error occurred while deleting type of product", null));
        }
    }
    @Override
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Product> list = productRepository.findAll();
            if (list.isEmpty()) {
                return ResponseEntity.status(404).body(new Response<>(false, "No products found", null));
            }
            List<ProductDto> dtos = list.stream().map(ProductMapper::ProductDtoToMapper).collect(Collectors.toList());
            return ResponseEntity.ok(new Response<>(true, "Retrieved all products successfully", dtos));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response<>(false, "Error retrieving products", null));
        }
    }

    @Override
    public ResponseEntity<?> getProductById(Long id) {
        try {
            Optional<Product> opt = productRepository.findById(id);
            if (opt.isEmpty()) {
                return ResponseEntity.status(404).body(new Response<>(false, "Product not found", null));
            }
            return ResponseEntity.ok(new Response<>(true, "Product found", ProductMapper.ProductDtoToMapper(opt.get())));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response<>(false, "Error retrieving product", null));
        }
    }

    @Override
    public ResponseEntity<?> createProduct(ProductCreate dto) {
        try {
            if (productRepository.existsByName(dto.getName())) {
                return ResponseEntity.status(200).body(new Response<>(false, "Product with this name already exists", null));
            }

            Optional<TypeOfProduct> typeOpt = typeOfProductRepository.findById(dto.getTypeOfProductId());
            if (typeOpt.isEmpty()) {
                return ResponseEntity.status(404).body(new Response<>(false, "Type of product not found", null));
            }

            Product p = Product.builder()
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .unit(dto.getUnit())
                    .typeOfProduct(typeOpt.get())
                    .build();

            Product saved = productRepository.save(p);
            return ResponseEntity.status(201).body(new Response<>(true, "Product created successfully", ProductMapper.ProductDtoToMapper(saved)));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response<>(false, "Error creating product", null));
        }
    }

    @Override
    public ResponseEntity<?> updateProduct(ProductUpdate dto) {
        try {
            Optional<Product> opt = productRepository.findById(dto.getId());
            if (opt.isEmpty()) {
                return ResponseEntity.status(404).body(new Response<>(false, "Product not found", null));
            }

            Optional<Product> nameDup = productRepository.findByName(dto.getName());
            if (nameDup.isPresent() && nameDup.get().getId() != dto.getId()) {
                return ResponseEntity.status(400).body(new Response<>(false, "Another product with this name already exists", null));
            }

            Optional<TypeOfProduct> typeOpt = typeOfProductRepository.findById(dto.getTypeOfProductId());
            if (typeOpt.isEmpty()) {
                return ResponseEntity.status(404).body(new Response<>(false, "Type of product not found", null));
            }

            Product p = opt.get();
            p.setName(dto.getName());
            p.setDescription(dto.getDescription());
            p.setUnit(dto.getUnit());
            p.setTypeOfProduct(typeOpt.get());

            Product updated = productRepository.save(p);
            return ResponseEntity.ok(new Response<>(true, "Product updated successfully", ProductMapper.ProductDtoToMapper(updated)));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response<>(false, "Error updating product", null));
        }
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long id) {
        try {
            if (!productRepository.existsById(id)) {
                return ResponseEntity.status(404).body(new Response<>(false, "Product not found", null));
            }
            productRepository.deleteById(id);
            return ResponseEntity.ok(new Response<>(true, "Product deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response<>(false, "Error deleting product", null));
        }
    }
}
