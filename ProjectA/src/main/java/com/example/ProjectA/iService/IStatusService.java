package com.example.ProjectA.iService;

import com.example.ProjectA.dto.StatusDto;
import com.example.ProjectA.entity.Status;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IStatusService {
    ResponseEntity<?> getAllStatuses();
    ResponseEntity<?> getStatusById(Long id);
    ResponseEntity<?> createStatus(StatusDto statusDto);
    ResponseEntity<?> updateStatus(Long id, StatusDto statusDto);
    ResponseEntity<?> deleteStatus(Long id);

}