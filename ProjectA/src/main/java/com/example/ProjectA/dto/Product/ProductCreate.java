package com.example.ProjectA.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreate {
    private String name;
    private String description;
    private String unit;
    private Long typeOfProductId;
}