package com.dh.DentalClinicJuanda.DTO;

public class DentistDTO {

    private Long id;
    private String lastName;
    private String name;
    private Integer registration;

    // Constructor vacío (necesario para deserialización)
    public DentistDTO() {}

    // Constructor con parámetros
    public DentistDTO(Long id, String lastName, String name, Integer registration) {
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.registration = registration;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRegistration() {
        return registration;
    }

    public void setRegistration(Integer registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "DentistDTO{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", registration=" + registration +
                '}';
    }
}