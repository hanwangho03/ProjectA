package com.example.ProjectA.dto.Supplier;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SupplierCreate {
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
}
