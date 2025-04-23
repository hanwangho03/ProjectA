package com.example.ProjectA.Mapper;

import com.example.ProjectA.dto.Supplier.SupplierCreate;
import com.example.ProjectA.dto.Supplier.SupplierDto;
import com.example.ProjectA.entity.Supplier;

public class SupplierMapper {
    public static SupplierDto toDto(Supplier supplier) {
        SupplierDto dto = new SupplierDto();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setAddress(supplier.getAddress());
        dto.setPhone(supplier.getPhone());
        return dto;
    }

    public static Supplier toEntity(SupplierCreate dto) {
        return Supplier.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .build();
    }
}