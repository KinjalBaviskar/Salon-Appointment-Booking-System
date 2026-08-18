package com.example.salon.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.salon.dto.AppointmentRequestDTO;
import com.example.salon.dto.AppointmentResponseDTO;
import com.example.salon.entity.Appointment;
import com.example.salon.entity.Customer;
import com.example.salon.entity.EmployeeServiceMapping;
import com.example.salon.entity.Status;
import com.example.salon.exception.ResourceNotFoundException;
import com.example.salon.repository.AppointmentRepository;
import com.example.salon.repository.CustomerRepository;
import com.example.salon.repository.EmployeeServiceMappingRepository;
import com.example.salon.repository.StatusRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeServiceMappingRepository mappingRepository;

    @Autowired
    private StatusRepository statusRepository;

    // Create Appointment
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO dto) {

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found"));

        EmployeeServiceMapping mapping = mappingRepository.findById(dto.getEmployeeServiceMappingId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee Service Mapping not found"));

        Status status = statusRepository.findByName("Booked")
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booked status not found"));

        boolean alreadyBooked =
                appointmentRepository.existsByEmployeeServiceMapping_IdAndAppointmentDateAndAppointmentTime(
                        dto.getEmployeeServiceMappingId(),
                        dto.getAppointmentDate(),
                        dto.getAppointmentTime());

        if (alreadyBooked) {
            throw new RuntimeException("Selected slot is already booked.");
        }

        Appointment appointment = new Appointment();

        appointment.setBookingId(generateBookingId());

        appointment.setCustomer(customer);
        appointment.setEmployeeServiceMapping(mapping);
        appointment.setStatus(status);

        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());

        appointment.setTotalPrice(
                mapping.getService().getPrice());

        Appointment saved = appointmentRepository.save(appointment);

        return convertToDTO(saved);
    }
        public List<AppointmentResponseDTO> getAppointmentsByCustomer(
                Integer customerId) {

        return appointmentRepository
                .findByCustomerId(customerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        }

    // Get All
    public List<AppointmentResponseDTO> getAllAppointments() {

        return appointmentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get By Id
    public AppointmentResponseDTO getAppointmentById(Integer id) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Appointment not found"));

        return convertToDTO(appointment);
    }

    // Delete
    public void deleteAppointment(Integer id) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Appointment not found"));

        appointmentRepository.delete(appointment);
    }
        
    // Generate Booking ID
    private String generateBookingId() {

        long count = appointmentRepository.count() + 1;

        return "SAL" + String.format("%05d", count);
    }

    // Entity -> DTO
    private AppointmentResponseDTO convertToDTO(Appointment appointment) {

        AppointmentResponseDTO dto = new AppointmentResponseDTO();

        dto.setId(appointment.getId());

        dto.setBookingId(appointment.getBookingId());

        dto.setCustomerName(
                appointment.getCustomer().getName());

        dto.setEmployeeName(
                appointment.getEmployeeServiceMapping()
                        .getEmployee()
                        .getName());

        dto.setServiceName(
                appointment.getEmployeeServiceMapping()
                        .getService()
                        .getServiceName());

        dto.setStatus(
                appointment.getStatus().getName());

        dto.setAppointmentDate(
                appointment.getAppointmentDate());

        dto.setAppointmentTime(
                appointment.getAppointmentTime());

        dto.setTotalPrice(
                appointment.getTotalPrice());

        return dto;
    }
}