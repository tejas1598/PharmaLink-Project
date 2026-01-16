package com.rackathon.pharmaLink.service;

import com.rackathon.pharmaLink.entity.Medicine;
import com.rackathon.pharmaLink.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;

@Service // <--- KEY: Tells Spring "This is where the logic happens"
public class MedicineService {

    // Add this method to find a single medicine
    public Medicine getMedicineById(Long id) {
        return medicineRepository.findById(id).orElse(null);
    }

    // Ensure you have a save method (you probably already do for "add")
    public Medicine saveMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }
    @Autowired
    private MedicineRepository medicineRepository;


    // Feature 1: Get All (With Logic)
    public List<Medicine> getAllMedicines() {
        // Later, we can add logic here (e.g., "Don't show deleted items")
        return medicineRepository.findAll();
    }

    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }

    // Feature 2: Add Medicine (With Logic)
    public Medicine addMedicine(Medicine medicine) {
        // LOGIC: Check if stock is negative (Double safety)
        if (medicine.getStockQuantity() < 0) {
            throw new IllegalArgumentException("Stock cannot be negative!");
        }
        return medicineRepository.save(medicine);
    }

    // Feature 3: The Search Logic
    public List<Medicine> searchMedicines(String name) {
        return medicineRepository.findByNameContainingIgnoreCase(name);

    }

    public List<Medicine> getExpiringMedicines() {
        // 1. Calculate the date 30 days from now
        LocalDate thresholdDate = LocalDate.now().plusDays(30);

        // 2. Ask the repo for medicines expiring before that date
        return medicineRepository.findByExpiryDateBefore(thresholdDate);
    }
}