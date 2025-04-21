package com.example.ProjectA.dto.Status;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {
    private  Long id;
    @NotBlank(message = "Tên trạng thái không được để trống")
    @NotNull(message = "Tên trạng thái không được để trống")
    @Max(value = 25, message = "Tên trạng thái không được quá 25 kí tự")
    private String name;
}