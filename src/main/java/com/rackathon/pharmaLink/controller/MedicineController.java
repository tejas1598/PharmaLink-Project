package com.rackathon.pharmaLink.controller;

import com.rackathon.pharmaLink.entity.Medicine;
import com.rackathon.pharmaLink.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    // ... inside MedicineController class

    @PutMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable Long id, @RequestBody Medicine medicineDetails) {
        // 1. Check if the medicine exists
        Medicine medicine = medicineService.getMedicineById(id); // You might need to add this to Service too

        if (medicine == null) {
            return ResponseEntity.notFound().build();
        }

        // 2. Update the fields
        medicine.setName(medicineDetails.getName());
        medicine.setPrice(medicineDetails.getPrice());
        medicine.setName(medicineDetails.getManufacturer());
        medicine.setExpiryDate(medicineDetails.getExpiryDate());
        medicine.setStockQuantity(medicineDetails.getStockQuantity());

        // 3. Save the updated version
        Medicine updatedMedicine = medicineService.saveMedicine(medicine); // Re-use your save method

        return ResponseEntity.ok(updatedMedicine);
    }
    @Autowired
    private MedicineService medicineService; // <--- CHANGED: Now using Service, not Repository

    @GetMapping
    public List<Medicine> getAllMedicines(@RequestParam(required = false) String name) {
        if (name != null) {
            return medicineService.searchMedicines(name);
        }
        return medicineService.getAllMedicines();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long id) {
        // Check if it exists first
        Medicine medicine = medicineService.getMedicineById(id);
        if (medicine == null) {
            return ResponseEntity.notFound().build();
        }

        medicineService.deleteMedicine(id); // You might need to create this in Service
        return ResponseEntity.noContent().build();
    }

    // Search medicines by name
    @GetMapping("/search")
    public List<Medicine> searchMedicines(@RequestParam String name) {
        return medicineService.searchMedicines(name);
    }

    @PostMapping
    public Medicine addMedicine(@Valid @RequestBody Medicine medicine) {
        return medicineService.addMedicine(medicine);
    }

    // GET /api/medicines/expiring
    @GetMapping("/expiring")
    public List<Medicine> getExpiringMedicines() {
        return medicineService.getExpiringMedicines();
    }
}