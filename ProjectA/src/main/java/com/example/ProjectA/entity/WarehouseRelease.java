package com.example.ProjectA.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "WarehouseRelease")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseRelease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime createdAt;

    @Min(value = 1, message = "Người tạo phải hợp lệ")
    private int createdBy;

    @Min(value = 1, message = "Trạng thái phải hợp lệ")
    private int statusId;

    private LocalDate dateOfTransport;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "warehouseRelease", cascade = CascadeType.ALL)
    private Set<WarehouseReleaseDetail> warehouseReleaseDetails;

    @ManyToOne
    @JoinColumn(name = "userId")
    private  User user;

}
