package com.rackathon.pharmaLink.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data; // This comes from Lombok dependency
import java.time.LocalDate;

@Entity
@Data // Generates Getters, Setters, toString, etc. automatically
@Table(name = "medicines")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String skuCode;

    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stockQuantity;

    private LocalDate expiryDate;

    // Price is crucial for billing!
    private Double price;

    public String getManufacturer() {
        return "";
    }
}