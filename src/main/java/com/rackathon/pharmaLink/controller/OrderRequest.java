package com.rackathon.pharmaLink.controller;

import lombok.Data;

@Data
public class OrderRequest {
    private Long hospitalId;
    private Long medicineId;
    private Integer quantity;
}