package com.example.ProjectA.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "WarehouseReceiptDetail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseReceiptDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    @ManyToOne
    @JoinColumn(name = "warehouseReceipt_Id")
    private WarehouseReceipt warehouseReceipt;

    @ManyToOne
    @JoinColumn(name = "batch_Id")
    private Batch batch;

    @Positive(message = "Số lượng phải lớn hơn 0")
    private double quantity;

    @DecimalMin(value = "0.0", inclusive = true, message = "Đơn giá không được âm")
    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "supplierProductId" )
    private SupplierProduct supplierProduct;

}
