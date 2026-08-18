package com.example.salon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.salon.entity.SalonService;

@Repository
public interface ServiceRepository extends JpaRepository<SalonService, Integer> {

    Optional<SalonService> findByServiceName(String serviceName);

    boolean existsByServiceName(String serviceName);

}