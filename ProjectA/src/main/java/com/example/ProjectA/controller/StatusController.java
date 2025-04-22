package com.example.ProjectA.controller;

import com.example.ProjectA.dto.Status.StatusCreate;
import com.example.ProjectA.dto.Status.StatusDto;
import com.example.ProjectA.dto.Status.StatusUpdate;
import com.example.ProjectA.iService.IStatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private  IStatusService statusService;

    @GetMapping
    public ResponseEntity<?> getAllStatuses() {
        return statusService.getAllStatuses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStatusById(@PathVariable Long id) {
        return statusService.getStatusById(id);
    }

    @PostMapping
    public ResponseEntity<?> createStatus(@Valid @RequestBody StatusCreate statusDto) {
        return statusService.createStatus(statusDto);
    }

    @PutMapping
    public ResponseEntity<?> updateStatus(@Valid @RequestBody StatusUpdate StatusUpdate) {
        return statusService.updateStatus(StatusUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable Long id) {
        return statusService.deleteStatus(id);
    }

}