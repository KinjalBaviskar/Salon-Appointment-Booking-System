package com.example.salon.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.salon.dto.ServiceRequestDTO;
import com.example.salon.dto.ServiceResponseDTO;
import com.example.salon.entity.SalonService;
import com.example.salon.exception.ResourceNotFoundException;
import com.example.salon.repository.ServiceRepository;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    // Create Service
    public ServiceResponseDTO createService(ServiceRequestDTO dto) {

        if (serviceRepository.existsByServiceName(dto.getServiceName())) {
            throw new RuntimeException("Service already exists");
        }

        SalonService service = new SalonService();

        service.setServiceName(dto.getServiceName());
        service.setDescription(dto.getDescription());
        service.setDuration(dto.getDuration());
        service.setPrice(dto.getPrice());

        SalonService savedService = serviceRepository.save(service);

        return convertToDTO(savedService);
    }

    // Get All Services
    public List<ServiceResponseDTO> getAllServices() {

        return serviceRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get Service By Id
    public ServiceResponseDTO getServiceById(Integer id) {

        SalonService service = serviceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Service not found with id : " + id));

        return convertToDTO(service);
    }

    // Update Service
    public ServiceResponseDTO updateService(Integer id, ServiceRequestDTO dto) {

        SalonService service = serviceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Service not found with id : " + id));

        service.setServiceName(dto.getServiceName());
        service.setDescription(dto.getDescription());
        service.setDuration(dto.getDuration());
        service.setPrice(dto.getPrice());

        SalonService updatedService = serviceRepository.save(service);

        return convertToDTO(updatedService);
    }

    // Delete Service
    public void deleteService(Integer id) {

        SalonService service = serviceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Service not found with id : " + id));

        serviceRepository.delete(service);
    }

    // Convert Entity to DTO
    private ServiceResponseDTO convertToDTO(SalonService service) {

        ServiceResponseDTO dto = new ServiceResponseDTO();

        dto.setId(service.getId());
        dto.setServiceName(service.getServiceName());
        dto.setDescription(service.getDescription());
        dto.setDuration(service.getDuration());
        dto.setPrice(service.getPrice());

        return dto;
    }
}