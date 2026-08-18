package com.example.salon.dto;

import jakarta.validation.constraints.NotNull;

public class EmployeeServiceMappingRequestDTO {

    @NotNull(message="Employee Id is required")
    private Integer employeeId;

    @NotNull(message="Service Id is required")
    private Integer serviceId;

    public EmployeeServiceMappingRequestDTO() {
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

}