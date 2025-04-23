package com.example.ProjectA.dto.WarehouseReceipt;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseReceiptCreate {

    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal total;

    private Long userId;
    private Long statusId;
}
