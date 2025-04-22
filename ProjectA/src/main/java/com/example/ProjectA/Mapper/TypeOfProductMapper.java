package com.example.ProjectA.Mapper;


import com.example.ProjectA.entity.TypeOfProduct;

public class TypeOfProductMapper {
    public static TypeOfProduct TypeOfProductToMapper(TypeOfProduct type) {
        TypeOfProduct typeOfProduct = new TypeOfProduct();
        typeOfProduct.setId(type.getId());
        typeOfProduct.setName(type.getName());
        typeOfProduct.setRequiredTemperature(type.getRequiredTemperature());
        typeOfProduct.setRequiredTemperature(type.getRequiredTemperature());
        return typeOfProduct;
    }
}
