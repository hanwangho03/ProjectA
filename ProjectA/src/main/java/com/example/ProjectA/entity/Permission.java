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
    private Long id;


    private String name;

    private String Actions;

    @OneToMany(mappedBy = "permission", cascade = CascadeType.ALL)
    private Set<RolePermission> rolePermission;

}
