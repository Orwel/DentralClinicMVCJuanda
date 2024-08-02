package com.dh.DentalClinicJuanda.service.Implementation;

import com.dh.DentalClinicJuanda.DTO.DentistDTO;
import com.dh.DentalClinicJuanda.entity.Dentist;
import com.dh.DentalClinicJuanda.exception.ResourceNotFoundException;
import com.dh.DentalClinicJuanda.repository.IDentistRepository;
import com.dh.DentalClinicJuanda.service.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DentistService implements IDentistService {

    private final IDentistRepository dentistRepository;

    @Autowired
    public DentistService(IDentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    @Override
    public DentistDTO save(DentistDTO dentistDTO) {
        Dentist dentist = convertToEntity(dentistDTO);
        Dentist savedDentist = dentistRepository.save(dentist);
        return convertToDTO(savedDentist);
    }

    @Override
    public Optional<DentistDTO> findById(Long id) {
        Optional<Dentist> dentist = dentistRepository.findById(id);
        return dentist.map(this::convertToDTO);
    }

    @Override
    public Optional<DentistDTO> update(Long id, DentistDTO dentistDTO) {
        if (!dentistRepository.existsById(id)) {
            return Optional.empty();
        }
        Dentist dentist = convertToEntity(dentistDTO);
        dentist.setId(id);
        Dentist updatedDentist = dentistRepository.save(dentist);
        return Optional.of(convertToDTO(updatedDentist));
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        if (!dentistRepository.existsById(id)) {
            throw new ResourceNotFoundException("Dentist not found with id: " + id);
        }
        dentistRepository.deleteById(id);
    }

    @Override
    public List<DentistDTO> findAll() {
        List<Dentist> dentists = dentistRepository.findAll();
        return dentists.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private DentistDTO convertToDTO(Dentist dentist) {
        return new DentistDTO(dentist.getId(), dentist.getLastName(), dentist.getName(), dentist.getRegistration());
    }

    private Dentist convertToEntity(DentistDTO dentistDTO) {
        return new Dentist(dentistDTO.getId(), dentistDTO.getLastName(), dentistDTO.getName(), dentistDTO.getRegistration());
    }
}