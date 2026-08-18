package com.example.salon.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.salon.entity.Appointment;

public interface AppointmentRepository
        extends JpaRepository<Appointment,Integer>{

    Optional<Appointment> findByBookingId(String bookingId);
    List<Appointment> findByCustomerId(Integer customerId);
    boolean existsByEmployeeServiceMapping_IdAndAppointmentDateAndAppointmentTime(
            Integer mappingId,
            LocalDate appointmentDate,
            LocalTime appointmentTime);

}