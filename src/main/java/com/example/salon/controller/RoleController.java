package com.example.salon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.salon.dto.RoleRequestDTO;
import com.example.salon.dto.RoleResponseDTO;
import com.example.salon.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/roles")
@Validated
public class RoleController {

    @Autowired
    private RoleService roleService;

    // Create Role

    @PostMapping
    public ResponseEntity<RoleResponseDTO> createRole(
            @Valid @RequestBody RoleRequestDTO dto) {

        return new ResponseEntity<>(
                roleService.createRole(dto),
                HttpStatus.CREATED);
    }

    // Get All Roles
    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getAllRoles() {

        return ResponseEntity.ok(roleService.getAllRoles());
    }

    // Get Role By Id
    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> getRoleById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    // Update Role
    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> updateRole(
            @PathVariable Integer id,
            @Valid @RequestBody RoleRequestDTO dto) {

        return ResponseEntity.ok(
                roleService.updateRole(id, dto));
    }

    // Delete Role
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(
            @PathVariable Integer id) {

        roleService.deleteRole(id);

        return ResponseEntity.ok("Role deleted successfully.");
    }

}