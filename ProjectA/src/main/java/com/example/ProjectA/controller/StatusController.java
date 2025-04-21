package com.example.ProjectA.controller;

import com.example.ProjectA.dto.StatusDto;
import com.example.ProjectA.entity.Status;
import com.example.ProjectA.iService.IStatusService;
import com.example.ProjectA.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statuses")
@RequiredArgsConstructor
public class StatusController {

    private final IStatusService statusService;

    @GetMapping
    public ResponseEntity<?> getAllStatuses() {
        return statusService.getAllStatuses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStatusById(@PathVariable Long id) {
        return statusService.getStatusById(id);
    }

    @PostMapping
    public ResponseEntity<?> createStatus(@RequestBody StatusDto statusDto) {
        return statusService.createStatus(statusDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody StatusDto statusDto) {
        return statusService.updateStatus(id, statusDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable Long id) {
        return statusService.deleteStatus(id);
    }

}