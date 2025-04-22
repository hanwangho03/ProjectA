package com.example.ProjectA.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "WarehouseReleaseDetail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseReleaseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    @ManyToOne
    @JoinColumn(name = "warehouseReleaseId", nullable = false)
    private WarehouseRelease warehouseRelease;

    @ManyToOne
    @JoinColumn(name = "batchId", nullable = false)
    private Batch batch;

    @Positive(message = "Số lượng phải lớn hơn 0")
    private double quantity;
}
