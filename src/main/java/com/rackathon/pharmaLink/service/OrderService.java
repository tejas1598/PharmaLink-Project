package com.rackathon.pharmaLink.service;
import java.util.List;

import com.rackathon.pharmaLink.entity.Hospital;
import com.rackathon.pharmaLink.entity.Medicine;
import com.rackathon.pharmaLink.entity.Order;
import com.rackathon.pharmaLink.repository.HospitalRepository;
import com.rackathon.pharmaLink.repository.MedicineRepository;
import com.rackathon.pharmaLink.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    // The Master Method: Takes IDs and connects everything
    public Order placeOrder(Long hospitalId, Long medicineId, int quantity) {

        // 1. Find the Hospital (Throw error if not found)
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new RuntimeException("Hospital not found!"));

        // 2. Find the Medicine
        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new RuntimeException("Medicine not found!"));

        // 3. CHECK STOCK (The Business Logic)
        if (medicine.getStockQuantity() < quantity) {
            throw new RuntimeException("Not enough stock! Available: " + medicine.getStockQuantity());
        }

        // 4. SUBTRACT STOCK (Update Inventory)
        medicine.setStockQuantity(medicine.getStockQuantity() - quantity);
        medicineRepository.save(medicine); // Save the new stock level

        // 5. CREATE AND SAVE ORDER
        Order order = new Order();
        order.setHospital(hospital);  // Link the Hospital
        order.setMedicine(medicine);  // Link the Medicine
        order.setQuantity(quantity);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("COMPLETED");

        return orderRepository.save(order);
    }

    // Feature: Get Order History for a specific Hospital
    public List<Order> getOrdersByHospital(Long hospitalId) {
        return orderRepository.findByHospitalId(hospitalId);
    }
}