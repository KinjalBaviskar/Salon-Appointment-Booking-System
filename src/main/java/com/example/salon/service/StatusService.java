package com.example.salon.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.salon.dto.StatusRequestDTO;
import com.example.salon.dto.StatusResponseDTO;
import com.example.salon.entity.Status;
import com.example.salon.exception.ResourceNotFoundException;
import com.example.salon.repository.StatusRepository;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public StatusResponseDTO createStatus(StatusRequestDTO dto) {

        if (statusRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Status already exists");
        }

        Status status = new Status();
        status.setName(dto.getName());

        return convertToDTO(statusRepository.save(status));
    }

    public List<StatusResponseDTO> getAllStatus() {
        return statusRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public StatusResponseDTO getStatusById(Integer id) {

        Status status = statusRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Status not found"));

        return convertToDTO(status);
    }

    public StatusResponseDTO updateStatus(Integer id, StatusRequestDTO dto) {

        Status status = statusRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Status not found"));

        status.setName(dto.getName());

        return convertToDTO(statusRepository.save(status));
    }

    public void deleteStatus(Integer id) {

        Status status = statusRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Status not found"));

        statusRepository.delete(status);
    }

    private StatusResponseDTO convertToDTO(Status status) {

        return new StatusResponseDTO(
                status.getId(),
                status.getName());
    }
}