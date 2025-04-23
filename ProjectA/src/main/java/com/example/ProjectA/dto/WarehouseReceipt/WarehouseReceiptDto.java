package com.example.ProjectA.dto.WarehouseReceipt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseReceiptDto {
    private Long id;
    private LocalDateTime createdAt;
    private Long userId;
    private BigDecimal total;
    private String supplierName;
    private Long statusId;
    private String statusName;
}