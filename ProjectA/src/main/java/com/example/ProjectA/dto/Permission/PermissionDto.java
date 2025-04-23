package com.example.ProjectA.dto.Permission;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionDto {
    private Long id;

    @NotNull(message = "Name không được để trống")
    @NotBlank(message = "Name không được để rỗng")

    private String name;

    @NotNull(message = "Actions không được để trống")
    @NotBlank(message = "Actions không được để rỗng")

    private String actions;
}
