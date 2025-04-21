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
public class Role {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "role" , cascade = CascadeType.ALL)
    private Set<rolePermission> rolePermission;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<User> user;

}
