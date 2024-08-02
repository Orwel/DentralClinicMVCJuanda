package com.dh.DentalClinicJuanda.repository;

import com.dh.DentalClinicJuanda.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iPatientRepository extends JpaRepository<Patient, Long> {
}
