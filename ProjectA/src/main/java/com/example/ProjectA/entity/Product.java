package com.example.ProjectA.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(max = 100, message = "Tên sản phẩm không được vượt quá 100 ký tự")
    @Column(nullable = false, length = 100)
    private String name;

    @Size(max = 1000, message = "Mô tả không được vượt quá 1000 ký tự")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Loại sản phẩm không được để trống")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TypeOfProductId", nullable = false)
    private TypeOfProduct typeOfProduct;

    @NotBlank(message = "Đơn vị không được để trống")
    @Size(max = 50, message = "Đơn vị không được vượt quá 50 ký tự")
    @Column(nullable = false, length = 50)
    private String unit;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<SupplierProduct> supplierProducts;
}
