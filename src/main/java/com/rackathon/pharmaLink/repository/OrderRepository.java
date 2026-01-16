package com.rackathon.pharmaLink.repository;
import java.util.List;

import com.rackathon.pharmaLink.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Magic Method: Spring sees "HospitalId" and knows to look at the "hospital" relationship
    List<Order> findByHospitalId(Long hospitalId);
}