package com.dh.DentalClinicJuanda.repository;

import com.dh.DentalClinicJuanda.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDentistRepository extends JpaRepository<Dentist, Long>{
    //HQL o JPQL
//    @Query("SELECT d FROM Dentist d WHERE d.registration=?1")
    Optional<Dentist> findByRegistration(Integer registration);
}
