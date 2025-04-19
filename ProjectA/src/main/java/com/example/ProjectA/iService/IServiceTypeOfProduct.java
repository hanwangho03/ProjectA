package com.example.ProjectA.iService;


import com.example.ProjectA.entity.TypeOfProduct;

import java.util.List;
import java.util.Optional;

public interface IServiceTypeOfProduct {
    List<TypeOfProduct> getAllTypeOfProducts();
    Optional<TypeOfProduct> getTypeOfProduct(long id);
    TypeOfProduct addTypeOfProduct(TypeOfProduct typeOfProduct);
    TypeOfProduct updateTypeOfProduct(Long id, TypeOfProduct typeOfProduct);
    void deleteTypeOfProduct(TypeOfProduct typeOfProduct);
}
