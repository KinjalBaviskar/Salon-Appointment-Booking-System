package com.example.salon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.salon.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {

    Optional<Status> findByName(String name);

    boolean existsByName(String name);

}