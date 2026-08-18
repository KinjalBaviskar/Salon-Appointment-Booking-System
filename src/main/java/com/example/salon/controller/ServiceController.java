package com.example.salon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.salon.dto.ServiceRequestDTO;
import com.example.salon.dto.ServiceResponseDTO;
import com.example.salon.service.ServiceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/services")
@Validated
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping
    public ResponseEntity<ServiceResponseDTO> createService(
            @Valid @RequestBody ServiceRequestDTO dto) {

        return new ResponseEntity<>(
                serviceService.createService(dto),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ServiceResponseDTO>> getAllServices() {

        return ResponseEntity.ok(serviceService.getAllServices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> getServiceById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(serviceService.getServiceById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> updateService(
            @PathVariable Integer id,
            @Valid @RequestBody ServiceRequestDTO dto) {

        return ResponseEntity.ok(
                serviceService.updateService(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteService(
            @PathVariable Integer id) {

        serviceService.deleteService(id);

        return ResponseEntity.ok("Service deleted successfully.");
    }
}