package com.example.ProjectA.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "Supplier")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên nhà cung cấp không được để rỗng")
    @Size(max = 100, message = "Tên nhà cung cấp không vượt quá 100 ký tự")
    @Column(length = 100, nullable = false)
    private String name;

    @Size(max = 255, message = "Địa chỉ không vượt quá 255 ký tự")
    @Column(length = 255)
    private String address;

    @Size(max = 20, message = "Số điện thoại không vượt quá 20 ký tự")
    @Column(length = 20)
    private String phone;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private Set<SupplierProduct> supplierProducts;
}
