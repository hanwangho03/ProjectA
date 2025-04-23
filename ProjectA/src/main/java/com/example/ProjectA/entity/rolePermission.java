package com.example.ProjectA.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "permissionId")
    private Permission permission;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @NotNull(message =  "Create Date không được trống")
    private Date createDate;

}
