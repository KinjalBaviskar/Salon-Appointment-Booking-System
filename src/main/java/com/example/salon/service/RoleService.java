package com.example.salon.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.salon.dto.RoleRequestDTO;
import com.example.salon.dto.RoleResponseDTO;
import com.example.salon.entity.Role;
import com.example.salon.exception.ResourceNotFoundException;
import com.example.salon.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // Create Role
    public RoleResponseDTO createRole(RoleRequestDTO dto) {

        if (roleRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Role already exists");
        }

        Role role = new Role();
        role.setName(dto.getName());

        Role savedRole = roleRepository.save(role);

        return convertToDTO(savedRole);
    }

    // Get All Roles
    public List<RoleResponseDTO> getAllRoles() {

        return roleRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get Role By Id
    public RoleResponseDTO getRoleById(Integer id) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found with id : " + id));

        return convertToDTO(role);
    }

    // Update Role
    public RoleResponseDTO updateRole(Integer id, RoleRequestDTO dto) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found with id : " + id));

        role.setName(dto.getName());

        Role updatedRole = roleRepository.save(role);

        return convertToDTO(updatedRole);
    }

    // Delete Role
    public void deleteRole(Integer id) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found with id : " + id));

        roleRepository.delete(role);
    }

    // Entity -> DTO
    private RoleResponseDTO convertToDTO(Role role) {

        RoleResponseDTO dto = new RoleResponseDTO();

        dto.setId(role.getId());
        dto.setName(role.getName());

        return dto;
    }
}