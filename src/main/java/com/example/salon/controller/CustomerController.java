package com.example.salon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.salon.dto.CustomerRequestDTO;
import com.example.salon.dto.CustomerResponseDTO;
import com.example.salon.service.CustomerService;

import jakarta.validation.Valid;
import com.example.salon.dto.LoginRequestDTO;
import com.example.salon.dto.LoginResponseDTO;

@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Register Customer
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> registerCustomer(
            @Valid @RequestBody CustomerRequestDTO dto) {

        CustomerResponseDTO response =
                customerService.registerCustomer(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get All Customers
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {

        List<CustomerResponseDTO> customers =
                customerService.getAllCustomers();

        return ResponseEntity.ok(customers);
    }

    // Get Customer By Id
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(
            @PathVariable Integer id) {

        CustomerResponseDTO customer =
                customerService.getCustomerById(id);

        return ResponseEntity.ok(customer);
    }

    // Update Customer
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(
            @PathVariable Integer id,
            @Valid @RequestBody CustomerRequestDTO dto) {

        CustomerResponseDTO customer =
                customerService.updateCustomer(id, dto);

        return ResponseEntity.ok(customer);
    }

    // Delete Customer
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(
            @PathVariable Integer id) {

        customerService.deleteCustomer(id);

        return ResponseEntity.ok("Customer deleted successfully.");
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO dto) {

        LoginResponseDTO response = customerService.login(dto);

        return ResponseEntity.ok(response);
    }
}