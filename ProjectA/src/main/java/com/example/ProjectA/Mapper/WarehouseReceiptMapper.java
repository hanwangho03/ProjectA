package com.example.ProjectA.Mapper;

import com.example.ProjectA.dto.WarehouseReceipt.WarehouseReceiptDto;
import com.example.ProjectA.entity.WarehouseReceipt;

public class WarehouseReceiptMapper {
    public static WarehouseReceiptDto WarehouseReceiptToMapper(WarehouseReceipt entity) {
        WarehouseReceiptDto dto = new WarehouseReceiptDto();
        dto.setId(entity.getId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setTotal(entity.getTotal());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        if (entity.getStatus() != null) {
            dto.setStatusId(entity.getStatus().getId());
            dto.setStatusName(entity.getStatus().getName());
        }
        return dto;
    }
}
