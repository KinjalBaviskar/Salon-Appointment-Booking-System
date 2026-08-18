package com.example.salon.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public class AppointmentRequestDTO {

    @NotNull(message = "Customer Id is required")
    private Integer customerId;

    @NotNull(message = "Employee Service Mapping Id is required")
    private Integer employeeServiceMappingId;

    @NotNull(message = "Appointment date is required")
    @FutureOrPresent(message = "Appointment date cannot be in the past")
    private LocalDate appointmentDate;

    @NotNull(message = "Appointment time is required")
    private LocalTime appointmentTime;

    public AppointmentRequestDTO() {
    }

    public AppointmentRequestDTO(Integer customerId,
                                 Integer employeeServiceMappingId,
                                 LocalDate appointmentDate,
                                 LocalTime appointmentTime) {
        this.customerId = customerId;
        this.employeeServiceMappingId = employeeServiceMappingId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getEmployeeServiceMappingId() {
        return employeeServiceMappingId;
    }

    public void setEmployeeServiceMappingId(Integer employeeServiceMappingId) {
        this.employeeServiceMappingId = employeeServiceMappingId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}