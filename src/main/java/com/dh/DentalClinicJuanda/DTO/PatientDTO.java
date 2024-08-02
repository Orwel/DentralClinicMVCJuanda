package com.dh.DentalClinicJuanda.DTO;

import java.time.LocalDate;

public class PatientDTO {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Integer cardIdentity;
    private LocalDate admissionOfDate;
    private AddressDTO address;

    // Constructor vacío (necesario para deserialización)
    public PatientDTO() {}

    // Constructor con parámetros
    public PatientDTO(Long id, String name, String lastName, String email, Integer cardIdentity, LocalDate admissionOfDate, AddressDTO address) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.cardIdentity = cardIdentity;
        this.admissionOfDate = admissionOfDate;
        this.address = address;
    }

    // Constructor simplificado para pruebas
    public PatientDTO(Long id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCardIdentity() {
        return cardIdentity;
    }

    public void setCardIdentity(Integer cardIdentity) {
        this.cardIdentity = cardIdentity;
    }

    public LocalDate getAdmissionOfDate() {
        return admissionOfDate;
    }

    public void setAdmissionOfDate(LocalDate admissionOfDate) {
        this.admissionOfDate = admissionOfDate;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", cardIdentity=" + cardIdentity +
                ", admissionOfDate=" + admissionOfDate +
                ", address=" + address +
                '}';
    }
}