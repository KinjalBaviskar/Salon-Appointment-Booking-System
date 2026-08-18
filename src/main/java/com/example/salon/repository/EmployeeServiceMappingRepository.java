package com.example.salon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.salon.entity.EmployeeServiceMapping;

public interface EmployeeServiceMappingRepository
        extends JpaRepository<EmployeeServiceMapping,Integer>{

    List<EmployeeServiceMapping> findByEmployeeId(Integer employeeId);

    List<EmployeeServiceMapping> findByServiceId(Integer serviceId);

}