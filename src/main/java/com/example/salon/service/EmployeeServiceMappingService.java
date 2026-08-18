package com.example.salon.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.salon.dto.EmployeeServiceMappingRequestDTO;
import com.example.salon.dto.EmployeeServiceMappingResponseDTO;
import com.example.salon.entity.Employee;
import com.example.salon.entity.EmployeeServiceMapping;
import com.example.salon.entity.SalonService;
import com.example.salon.exception.ResourceNotFoundException;
import com.example.salon.repository.EmployeeRepository;
import com.example.salon.repository.EmployeeServiceMappingRepository;
import com.example.salon.repository.ServiceRepository;

@Service
public class EmployeeServiceMappingService {

    @Autowired
    private EmployeeServiceMappingRepository mappingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    // Assign Service to Employee
    public EmployeeServiceMappingResponseDTO assignService(EmployeeServiceMappingRequestDTO dto) {

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id : " + dto.getEmployeeId()));

        SalonService service = serviceRepository.findById(dto.getServiceId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Service not found with id : " + dto.getServiceId()));

        EmployeeServiceMapping mapping = new EmployeeServiceMapping();

        mapping.setEmployee(employee);
        mapping.setService(service);

        EmployeeServiceMapping savedMapping = mappingRepository.save(mapping);

        return convertToDTO(savedMapping);
    }

    // Get All Mappings
    public List<EmployeeServiceMappingResponseDTO> getAllMappings() {

        return mappingRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get Mapping By Id
    public EmployeeServiceMappingResponseDTO getMappingById(Integer id) {

        EmployeeServiceMapping mapping = mappingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Mapping not found with id : " + id));

        return convertToDTO(mapping);
    }

    // Delete Mapping
    public void deleteMapping(Integer id) {

        EmployeeServiceMapping mapping = mappingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Mapping not found with id : " + id));

        mappingRepository.delete(mapping);
    }

    // Convert Entity to DTO
    private EmployeeServiceMappingResponseDTO convertToDTO(EmployeeServiceMapping mapping) {

        EmployeeServiceMappingResponseDTO dto = new EmployeeServiceMappingResponseDTO();

        dto.setId(mapping.getId());

        dto.setEmployeeId(mapping.getEmployee().getId());
        dto.setEmployeeName(mapping.getEmployee().getName());

        dto.setServiceId(mapping.getService().getId());
        dto.setServiceName(mapping.getService().getServiceName());

        return dto;
    }
}