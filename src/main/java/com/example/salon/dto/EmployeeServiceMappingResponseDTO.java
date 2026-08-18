package com.example.salon.dto;

public class EmployeeServiceMappingResponseDTO {

    private Integer id;

    private Integer employeeId;

    private String employeeName;

    private Integer serviceId;

    private String serviceName;

    public EmployeeServiceMappingResponseDTO(Integer id, Integer employeeId, String employeeName, Integer serviceId,
            String serviceName) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
    }

    public EmployeeServiceMappingResponseDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    // Generate Constructor

    // Generate Getters & Setters

}