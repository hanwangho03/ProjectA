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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Storagearea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255, message = "Tên khu vực lưu trữ không quá 255 ký tự")
    @NotBlank(message = "Tên khu vực lưu trữ không được để trống")
    private String name;

    @NotBlank(message = "Nhiệt độ không được để trống")
    private Double temperature;

    @NotNull(message = "Dung tích không được để trống")
    @Min(value = 0, message = "Dung tích phải lớn hơn 0")
    private Double capacity;

    @ManyToOne
    @JoinColumn(name = "WarhouseId", nullable = false)
    private Warehouse warehouse;
}
