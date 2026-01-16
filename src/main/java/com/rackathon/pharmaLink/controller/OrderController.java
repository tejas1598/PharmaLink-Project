package com.rackathon.pharmaLink.controller;
import java.util.List;

import com.rackathon.pharmaLink.entity.Order;
import com.rackathon.pharmaLink.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order placeOrder(@RequestBody OrderRequest request) {
        return orderService.placeOrder(
                request.getHospitalId(),
                request.getMedicineId(),
                request.getQuantity()
        );
    }

    // GET /api/orders/hospital/1
    @GetMapping("/hospital/{hospitalId}")
    public List<Order> getOrdersByHospital(@PathVariable Long hospitalId) {
        return orderService.getOrdersByHospital(hospitalId);
    }
}