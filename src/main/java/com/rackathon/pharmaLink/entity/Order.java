package com.rackathon.pharmaLink.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private LocalDateTime orderDate; // Tracks when the order happened

    private String status; // "PENDING", "COMPLETED", "CANCELLED"

    // RELATIONSHIP 1: Who ordered this?
    // "Many orders can belong to One Hospital"
    @ManyToOne
    @JoinColumn(name = "hospital_id") // Creates a column that stores the Hospital's ID
    private Hospital hospital;

    // RELATIONSHIP 2: What did they order?
    // "Many orders can feature One Medicine"
    @ManyToOne
    @JoinColumn(name = "medicine_id") // Creates a column that stores the Medicine's ID
    private Medicine medicine;
}