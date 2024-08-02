package com.dh.DentalClinicJuanda.controller;

import com.dh.DentalClinicJuanda.DTO.DentistDTO;
import com.dh.DentalClinicJuanda.service.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/dentists")
public class DentistController {

    private final IDentistService dentistService;

    @Autowired
    public DentistController(IDentistService dentistService) {
        this.dentistService = dentistService;
    }

    @GetMapping
    public ResponseEntity<List<DentistDTO>> findAll() {
        List<DentistDTO> dentist = dentistService.findAll();
        return ResponseEntity.ok(dentist);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistDTO> findById(@PathVariable Long id) {
        Optional<DentistDTO> dentist = dentistService.findById(id);
        return dentist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DentistDTO> save(@RequestBody DentistDTO dentistDTO) {
        DentistDTO savedDentist = dentistService.save(dentistDTO);
        return ResponseEntity.ok(savedDentist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DentistDTO> update(@PathVariable Long id, @RequestBody DentistDTO dentistDTO) {
        Optional<DentistDTO> updatedDentist = dentistService.update(id, dentistDTO);
        return updatedDentist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            dentistService.delete(id);
            return ResponseEntity.ok("Dentist deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Dentist not found");
        }
    }
}