package com.example.salon.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.salon.dto.EmployeeRequestDTO;
import com.example.salon.dto.EmployeeResponseDTO;
import com.example.salon.entity.Employee;
import com.example.salon.entity.Role;
import com.example.salon.exception.ResourceNotFoundException;
import com.example.salon.repository.EmployeeRepository;
import com.example.salon.repository.RoleRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Create Employee
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {

        if (employeeRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (employeeRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found with id : " + dto.getRoleId()));

        Employee employee = new Employee();

        employee.setName(dto.getName());
        employee.setUsername(dto.getUsername());
        employee.setPassword(passwordEncoder.encode(dto.getPassword()));
        employee.setPhone(dto.getPhone());
        employee.setEmail(dto.getEmail());
        employee.setExperience(dto.getExperience());
        employee.setRole(role);

        Employee savedEmployee = employeeRepository.save(employee);

        return convertToDTO(savedEmployee);
    }

    // Get All Employees
    public List<EmployeeResponseDTO> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get Employee By Id
    public EmployeeResponseDTO getEmployeeById(Integer id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id : " + id));

        return convertToDTO(employee);
    }

    // Update Employee
    public EmployeeResponseDTO updateEmployee(Integer id, EmployeeRequestDTO dto) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id : " + id));

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found with id : " + dto.getRoleId()));

        employee.setName(dto.getName());
        employee.setUsername(dto.getUsername());
        employee.setPassword(passwordEncoder.encode(dto.getPassword()));
        employee.setPhone(dto.getPhone());
        employee.setEmail(dto.getEmail());
        employee.setExperience(dto.getExperience());
        employee.setRole(role);

        Employee updatedEmployee = employeeRepository.save(employee);

        return convertToDTO(updatedEmployee);
    }

    // Delete Employee
    public void deleteEmployee(Integer id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id : " + id));

        employeeRepository.delete(employee);
    }

    // Convert Entity to DTO
    private EmployeeResponseDTO convertToDTO(Employee employee) {

        EmployeeResponseDTO dto = new EmployeeResponseDTO();

        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setUsername(employee.getUsername());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setExperience(employee.getExperience());

        dto.setRoleId(employee.getRole().getId());
        dto.setRoleName(employee.getRole().getName());

        return dto;
    }

}