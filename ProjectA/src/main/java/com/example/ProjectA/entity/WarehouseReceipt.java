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
    private Long id;

    private LocalDateTime createdAt;


    @DecimalMin(value = "0.0", inclusive = true, message = "Tổng tiền không được âm")
    private BigDecimal total;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy =  "warehouseReceipt",cascade = CascadeType.ALL)
    private Set<WarehouseReceiptDetail> warehouseReceiptDetail;

    @ManyToOne
    @JoinColumn(name = "statusId", nullable = false)
    private Status status;
}
