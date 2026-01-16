package com.rackathon.pharmaLink.repository;

import com.rackathon.pharmaLink.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    // We can add "findByName" later if we need it!
}