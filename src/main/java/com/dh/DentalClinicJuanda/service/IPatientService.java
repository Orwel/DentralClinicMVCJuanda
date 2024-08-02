package com.dh.DentalClinicJuanda.service;



import com.dh.DentalClinicJuanda.entity.Patient;
import com.dh.DentalClinicJuanda.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IPatientService {
    Patient save (Patient patient);
    Optional<Patient> findById(Long id);
    void update(Patient patient);
    void delete(Long id) throws ResourceNotFoundException;
    List<Patient> findAll();
}