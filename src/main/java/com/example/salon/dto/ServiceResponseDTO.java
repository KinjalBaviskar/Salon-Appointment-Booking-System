package com.example.salon.dto;

import java.math.BigDecimal;

public class ServiceResponseDTO {

    private Integer id;
    private String serviceName;
    private String description;
    private Integer duration;
    private BigDecimal price;

    public ServiceResponseDTO() {
    }

    public ServiceResponseDTO(Integer id, String serviceName,
                              String description,
                              Integer duration,
                              BigDecimal price) {
        this.id = id;
        this.serviceName = serviceName;
        this.description = description;
        this.duration = duration;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}