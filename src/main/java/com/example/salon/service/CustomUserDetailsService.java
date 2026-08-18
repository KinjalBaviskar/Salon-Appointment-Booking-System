package com.example.salon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.salon.entity.Customer;
import com.example.salon.entity.Employee;
import com.example.salon.repository.CustomerRepository;
import com.example.salon.repository.EmployeeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // First check Customer table
       Customer customer = customerRepository.findByUsernameWithRole(username)
        .orElse(null);

        if (customer != null) {

            return User.builder()
                    .username(customer.getUsername())
                    .password(customer.getPassword())
                    .roles(customer.getRole().getName())
                    .build();
        }

        // If not customer, check Employee table
        Employee employee = employeeRepository.findByUsernameWithRole(username)
        .orElse(null);  

        if (employee != null) {

            return User.builder()
                    .username(employee.getUsername())
                    .password(employee.getPassword())
                    .roles(employee.getRole().getName())
                    .build();
        }

        throw new UsernameNotFoundException(
                "User not found with username: " + username);
    }
}

