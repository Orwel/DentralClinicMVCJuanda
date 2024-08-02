package com.dh.DentalClinicJuanda.service.Implementation;

import com.dh.DentalClinicJuanda.repository.iPatientRepository;
import com.dh.DentalClinicJuanda.entity.Patient;
import com.dh.DentalClinicJuanda.exception.ResourceNotFoundException;
import com.dh.DentalClinicJuanda.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {
    private iPatientRepository patientRepository;

    @Autowired
    public PatientService(iPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public void update(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Optional<Patient> patientToLookFor = findById(id);
        if (patientToLookFor.isPresent()) {
            patientRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No se puedo eliminar el paciente con id: " + id);
        }
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
}
