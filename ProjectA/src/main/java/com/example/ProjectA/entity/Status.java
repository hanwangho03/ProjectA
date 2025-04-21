package com.example.ProjectA.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "Status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(length = 50, nullable = false)
    private String name;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private Set<WarehouseReceipt> warehouseReceipts;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private Set<WarehouseRelease> warehouseReleases;
}
