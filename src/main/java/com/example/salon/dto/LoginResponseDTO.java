package com.example.salon.dto;

public class LoginResponseDTO {

    private String message;
    private Integer customerId;
    private String username;
    private String role;

    // Default Constructor
    public LoginResponseDTO() {
    }

    // Parameterized Constructor
    public LoginResponseDTO(
            String message,
            Integer customerId,
            String username,
            String role) {

        this.message = message;
        this.customerId = customerId;
        this.username = username;
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}