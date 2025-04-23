package com.example.ProjectA.dto.Role;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUpdate {

    @NotNull(message = "Name không được để trống")
    @NotBlank(message = "Name không được để rỗng")
    private Long id;
    @Min(value = 0,message =  "Name phải có ít nhất 1 kí tự")
    @Max(value = 50,message = "Name chỉ cho phép tối đa 50 kí tự")
    @NotNull(message = "Name không được để trống")
    @NotBlank(message = "Name không được để rỗng")
    private String name;
}
