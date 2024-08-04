package com.dh.DentalClinicJuanda.repository;

import com.dh.DentalClinicJuanda.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // This method is used to find a user by email
}
