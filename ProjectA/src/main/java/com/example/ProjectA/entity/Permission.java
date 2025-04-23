package com.example.ProjectA.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

public class Permission {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @Min(value = 0,message =  "Name phải có ít nhất 1 kí tự")
    @Max(value = 50,message = "Name chỉ cho phép tối đa 50 kí tự")
    @NotNull(message = "Name không được để trống")
    @NotBlank(message = "Name không được để rỗng")
    private String name;

    @NotNull(message = "Action không được để trống")
    @Min(value = 0,message =  "Name phải có ít nhất 0 kí tự")
    @Max(value = 50,message = "Name chỉ cho phép tối đa 50 kí tự")
    private String Actions;

    @OneToMany(mappedBy = "permission", cascade = CascadeType.ALL)
    private Set<RolePermission> rolePermission;

}
