package com.example.salon.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.salon.dto.CustomerRequestDTO;
import com.example.salon.dto.CustomerResponseDTO;
import com.example.salon.entity.Customer;
import com.example.salon.repository.CustomerRepository;
import com.example.salon.repository.RoleRepository;
import com.example.salon.exception.ResourceNotFoundException;
import com.example.salon.repository.RoleRepository;
import com.example.salon.entity.Role;
import com.example.salon.dto.LoginRequestDTO;
import com.example.salon.dto.LoginResponseDTO;
import com.example.salon.exception.InvalidCredentialsException;
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

     @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    // Register Customer
    public CustomerResponseDTO registerCustomer(CustomerRequestDTO dto) {

        // Check duplicate username
        if (customerRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // Check duplicate email
        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

       Customer customer = new Customer();

        customer.setName(dto.getName());
        customer.setUsername(dto.getUsername());
        customer.setEmail(dto.getEmail());
        customer.setPassword(passwordEncoder.encode(dto.getPassword()));
        customer.setPhone(dto.getPhone());
        // Assign CUSTOMER role
        Role customerRole = roleRepository.findByName("Customer")
                .orElseThrow(() -> new RuntimeException("Customer role not found"));

        customer.setRole(customerRole);

        Customer savedCustomer = customerRepository.save(customer);

        return convertToResponseDTO(savedCustomer);
    }

    // Get All Customers
    public List<CustomerResponseDTO> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Get Customer By Id
    public CustomerResponseDTO getCustomerById(Integer id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        return convertToResponseDTO(customer);
    }

    // Update Customer
    public CustomerResponseDTO updateCustomer(Integer id,
                                              CustomerRequestDTO dto) {

        Customer customer = customerRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        customer.setName(dto.getName());
        customer.setUsername(dto.getUsername());
        customer.setEmail(dto.getEmail());
        customer.setPassword(passwordEncoder.encode(dto.getPassword()));
        customer.setPhone(dto.getPhone());

        Customer updatedCustomer = customerRepository.save(customer);

        return convertToResponseDTO(updatedCustomer);
    }

    // Delete Customer
    public void deleteCustomer(Integer id) {

        Customer customer = customerRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        customerRepository.delete(customer);
    }

    // Convert Entity -> Response DTO
    private CustomerResponseDTO convertToResponseDTO(Customer customer) {

        CustomerResponseDTO dto = new CustomerResponseDTO();

        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setUsername(customer.getUsername());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        
        return dto;
    }
    public LoginResponseDTO login(LoginRequestDTO dto) {

    Customer customer = customerRepository
            .findByUsernameWithRole(dto.getUsername())
            .orElseThrow(() ->
                    new InvalidCredentialsException("Invalid username or password"));

    boolean isMatch = passwordEncoder.matches(
            dto.getPassword(),
            customer.getPassword());

    if (!isMatch) {
        throw new InvalidCredentialsException("Invalid username or password");
    }

    return new LoginResponseDTO(
    "Login successful",
    customer.getId(),
    customer.getUsername(),
    customer.getRole().getName()
);
}

}