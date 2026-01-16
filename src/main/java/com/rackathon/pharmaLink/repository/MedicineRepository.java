package com.rackathon.pharmaLink.repository;

import java.time.LocalDate;
import com.rackathon.pharmaLink.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List; // Don't forget this import!



@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long>
{
    // Spring translates this to: SELECT * FROM medicines WHERE name LIKE '%query%'
    List<Medicine> findByNameContainingIgnoreCase(String name);
    // Let's add one for SKU too (Exact match)
    Medicine findBySkuCode(String skuCode);

    List<Medicine> findByExpiryDateBefore(LocalDate date);
}