package com.example.ProjectA.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TypeOfProduct")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeOfProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Tên loại sản phẩm không được để trống")
    @Size(max = 100, message = "Tên loại sản phẩm không được vượt quá 100 ký tự")
    @Column(nullable = false, length = 100)
    private String name;

    @Size(max = 50, message = "Nhiệt độ yêu cầu không được vượt quá 50 ký tự")
    @Column(length = 50)
    private String requiredTemperature;

    @Size(max = 1000, message = "Điều kiện bảo quản không được vượt quá 1000 ký tự")
    @Column(columnDefinition = "TEXT")
    private String storageConditions;
}
