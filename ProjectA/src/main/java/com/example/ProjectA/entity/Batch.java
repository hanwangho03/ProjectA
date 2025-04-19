package com.example.ProjectA.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50, message = "Mã kiện không được quá 50 ký tự")
    @NotBlank(message = "Mã kiện không được để trống")
    private String batchNumber;

    @NotBlank(message = "Ngày sản xuất không được để trống")
    private LocalDate manufactureDate;

    @NotBlank(message = "Hạn sử dụng không được để trống")
    private LocalDate expiryDate;

    @NotBlank(message = "Không được để trống số lượng")
    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    private Double quantity;

    @ManyToOne
    @JoinColumn(name = "StorageAreaId", nullable = false)
    private Storagearea storageArea;

    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = false)
    private Product product;

    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
    private Set<WarehouseReceiptDetail> warehouseReceiptDetails;
}