package com.example.ProjectA.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100, message = "Tên kho không quá 100 ký tự")
    @NotBlank(message = "Tên kho không được để trống")
    private String name;

    @Size(max = 255, message = "Địa điểm không được vượt quá 255 ký tự")
    private String location;

    @NotNull(message = "Dung tích không được để trống")
    @Min(value = 0, message = "Dung tích phải lớn hơn 0")
    private Double capacity;

    @Size(max = 50, message = "Phạm vi nhiệt độ không được vượt quá 50 ký tự")
    private String temperatureRange;
}
