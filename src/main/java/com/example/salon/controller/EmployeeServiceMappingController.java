package com.example.salon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.salon.dto.EmployeeServiceMappingRequestDTO;
import com.example.salon.dto.EmployeeServiceMappingResponseDTO;
import com.example.salon.service.EmployeeServiceMappingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee-service-mappings")
@Validated
public class EmployeeServiceMappingController {

    @Autowired
    private EmployeeServiceMappingService mappingService;

    @PostMapping
    public ResponseEntity<EmployeeServiceMappingResponseDTO> assignService(
            @Valid @RequestBody EmployeeServiceMappingRequestDTO dto) {

        return new ResponseEntity<>(
                mappingService.assignService(dto),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeServiceMappingResponseDTO>> getAllMappings() {

        return ResponseEntity.ok(mappingService.getAllMappings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeServiceMappingResponseDTO> getMappingById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(mappingService.getMappingById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMapping(
            @PathVariable Integer id) {

        mappingService.deleteMapping(id);

        return ResponseEntity.ok("Mapping deleted successfully.");
    }
}