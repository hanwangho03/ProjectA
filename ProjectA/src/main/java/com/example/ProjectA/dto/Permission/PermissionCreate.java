package com.example.ProjectA.dto.Permission;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionCreate {

    @NotNull(message = "Name không được để trống")
    @NotBlank(message = "Name không được để rỗng")
    //@Min(value = 1, message = "Name phải có ít nhất 1 kí tự")
//    @Max(value = 50, message = "Name chỉ cho phép tối đa 50 kí tự")
    private String name;

    @NotNull(message = "Actions không được để trống")
    @NotBlank(message = "Actions không được để rỗng")
    //@Min(value = 1, message = "Actions phải có ít nhất 1 kí tự")
//    @Max(value = 50, message = "Actions chỉ cho phép tối đa 50 kí tự")
    private String actions;
}
