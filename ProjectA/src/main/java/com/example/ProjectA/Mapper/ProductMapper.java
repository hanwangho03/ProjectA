package com.example.ProjectA.Mapper;

import com.example.ProjectA.dto.Product.ProductDto;
import com.example.ProjectA.entity.Product;

public class ProductMapper {
    public static ProductDto ProductDtoToMapper(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .unit(product.getUnit())
                .typeOfProductId(product.getTypeOfProduct().getId())
                .typeOfProductName(product.getTypeOfProduct().getName())
                .build();
    }
}
