package com.example.ProjectA.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemperatureLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "StorageAreaId", nullable = false)
    private Storagearea storageArea;

    @NotBlank(message = "Nhiệt độ không được để trống")
    private Double temperature;

    @NotBlank(message = "Mốc thời gian không được để trống")
    private LocalDateTime timeStamp;
}