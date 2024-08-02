package com.dh.DentalClinicJuanda.controller;


import com.dh.DentalClinicJuanda.entity.Patient;
import com.dh.DentalClinicJuanda.service.IPatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.dh.DentalClinicJuanda.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final IPatientService patientService;

    @Autowired
    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    //un endpoint que nos permita agregar un paciente
    @PostMapping
    public Patient save(@RequestBody Patient patient) {
        return patientService.save(patient);
    }

    //un endpoint que nos permita actualizar un paciente que ya esté registrado
    @PutMapping
    public void update(@RequestBody Patient patient) {
        patientService.update(patient);
    }

    @GetMapping
    public List<Patient> findAll() {
        return patientService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        patientService.delete(id);
        return ResponseEntity.ok("Se eliminó el paciente con id: " + id);
    }
}