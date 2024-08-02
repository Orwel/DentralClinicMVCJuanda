package com.dh.DentalClinicJuanda.service;

import com.dh.DentalClinicJuanda.DTO.DentistDTO;
import com.dh.DentalClinicJuanda.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IDentistService {
    DentistDTO save(DentistDTO dentistDTO);
    Optional<DentistDTO> findById(Long id);
    Optional<DentistDTO> update(Long id, DentistDTO dentistDTO);
    void delete(Long id) throws ResourceNotFoundException;
    List<DentistDTO> findAll();
}