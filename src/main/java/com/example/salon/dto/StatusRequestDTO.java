package com.example.salon.dto;

import jakarta.validation.constraints.NotBlank;

public class StatusRequestDTO {

    @NotBlank(message = "Status name is required")
    private String name;

    public StatusRequestDTO() {
    }

    public StatusRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}