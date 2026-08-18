package com.example.salon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.salon.dto.StatusRequestDTO;
import com.example.salon.dto.StatusResponseDTO;
import com.example.salon.service.StatusService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/status")
@Validated
public class StatusController {

    @Autowired
    private StatusService statusService;

    @PostMapping
    public ResponseEntity<StatusResponseDTO> createStatus(
            @Valid @RequestBody StatusRequestDTO dto) {

        return new ResponseEntity<>(
                statusService.createStatus(dto),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StatusResponseDTO>> getAllStatus() {

        return ResponseEntity.ok(statusService.getAllStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusResponseDTO> getStatusById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(statusService.getStatusById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusResponseDTO> updateStatus(
            @PathVariable Integer id,
            @Valid @RequestBody StatusRequestDTO dto) {

        return ResponseEntity.ok(
                statusService.updateStatus(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStatus(
            @PathVariable Integer id) {

        statusService.deleteStatus(id);

        return ResponseEntity.ok("Status deleted successfully.");
    }
}