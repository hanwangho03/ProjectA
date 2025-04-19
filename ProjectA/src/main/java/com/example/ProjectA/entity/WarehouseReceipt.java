package com.example.ProjectA.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "WarehouseReceipt")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime createdAt;

    @Min(value = 1, message = "Người tạo phải hợp lệ")
    private int createdBy;

    @Min(value = 1, message = "Trạng thái phải hợp lệ")
    private int statusId;

    @Min(value = 1, message = "Nhà cung cấp phải hợp lệ")
    private int supplierId;

    @DecimalMin(value = "0.0", inclusive = true, message = "Tổng tiền không được âm")
    private BigDecimal total;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "warehouseReceipt", cascade = CascadeType.ALL)
    private Set<WarehouseReceiptDetail> warehouseReceiptDetails;
}
