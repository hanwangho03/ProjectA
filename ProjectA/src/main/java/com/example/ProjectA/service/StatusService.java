package com.example.ProjectA.service;

import com.example.ProjectA.Helper.Response;
import com.example.ProjectA.Mapper.StatusMapper;
import com.example.ProjectA.dto.StatusDto;
import com.example.ProjectA.entity.Status;
import com.example.ProjectA.repository.StatusRepository;
import com.example.ProjectA.iService.IStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatusService implements IStatusService {

    private final StatusRepository statusRepository;

    @Override
    public ResponseEntity<?> getAllStatuses() {
        try {
            List<Status> statusList = statusRepository.findAll();
            if (statusList.isEmpty()) {
                return ResponseEntity.status(404).body(new Response<>(false, "No statuses found", null));
            }

            List<StatusDto> dtoList = statusList.stream()
                    .map(StatusMapper::StatusToMapper)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new Response<>(true, "Get all statuses successfully", dtoList));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response<>(false, "Something went wrong", null));
        }
    }

    @Override
    public ResponseEntity<?> getStatusById(Long id) {
        Optional<Status> optionalStatus = statusRepository.findById(id);
        if (optionalStatus.isPresent()) {
            StatusDto dto = StatusMapper.StatusToMapper(optionalStatus.get());
            return ResponseEntity.ok(new Response<>(true, "Status found", dto));
        }
        return ResponseEntity.status(404).body(new Response<>(false, "Status not found", null));
    }

    @Override
    public ResponseEntity<?> createStatus(StatusDto statusDto) {
        try {
            Status status = new Status();
            status.setName(statusDto.getName());
            Status savedStatus = statusRepository.save(status);
            return ResponseEntity.status(201).body(new Response<>(true, "Status created successfully", StatusMapper.StatusToMapper(savedStatus)));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response<>(false, "Error creating status", null));
        }
    }

    @Override
    public ResponseEntity<?> updateStatus(Long id, StatusDto statusDto) {
        Optional<Status> optionalStatus = statusRepository.findById(id);
        if (optionalStatus.isPresent()) {
            Status status = optionalStatus.get();
            status.setName(statusDto.getName());
            Status updatedStatus = statusRepository.save(status);
            return ResponseEntity.ok(new Response<>(true, "Status updated successfully", StatusMapper.StatusToMapper(updatedStatus)));
        }
        return ResponseEntity.status(404).body(new Response<>(false, "Status not found", null));
    }

    @Override
    public ResponseEntity<?> deleteStatus(Long id) {
        Optional<Status> optionalStatus = statusRepository.findById(id);
        if (optionalStatus.isPresent()) {
            statusRepository.deleteById(id);
            return ResponseEntity.ok(new Response<>(true, "Status deleted successfully", null));
        }
        return ResponseEntity.status(404).body(new Response<>(false, "Status not found", null));
    }
}