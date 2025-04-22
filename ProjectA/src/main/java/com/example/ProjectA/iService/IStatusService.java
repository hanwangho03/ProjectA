package com.example.ProjectA.iService;

import com.example.ProjectA.dto.Status.StatusCreate;
import com.example.ProjectA.dto.Status.StatusDto;
import com.example.ProjectA.dto.Status.StatusUpdate;
import org.springframework.http.ResponseEntity;

public interface IStatusService {
    ResponseEntity<?> getAllStatuses();
    ResponseEntity<?> getStatusById(Long id);
    ResponseEntity<?> createStatus(StatusCreate statusDto);
    ResponseEntity<?> updateStatus(StatusUpdate StatusUpdate);
    ResponseEntity<?> deleteStatus(Long id);

}