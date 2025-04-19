package com.example.ProjectA.service;

import com.example.ProjectA.entity.TypeOfProduct;
import com.example.ProjectA.iService.iServiceProduct;
import com.example.ProjectA.repository.ProductRepository;
import com.example.ProjectA.repository.TypeOfProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSerVice implements iServiceProduct {

    @Autowired
    private ProductRepository ProductRepository;

    @Autowired
    private TypeOfProductRepository TypeOfProductRepository;

    @Override
    public void abc() {

    }

    @Override
    public List<TypeOfProduct> getAllTypeOfProducts() {
        return List.of();
    }

    @Override
    public Optional<TypeOfProduct> getTypeOfProduct(long id) {
        return Optional.empty();
    }

    @Override
    public TypeOfProduct addTypeOfProduct(TypeOfProduct typeOfProduct) {
        return null;
    }

    @Override
    public TypeOfProduct updateTypeOfProduct(Long id, TypeOfProduct typeOfProduct) {
        return null;
    }

    @Override
    public void deleteTypeOfProduct(TypeOfProduct typeOfProduct) {

    }
}
