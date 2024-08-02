package com.dh.DentalClinicJuanda.DTO;

public class AddressDTO {
    private Integer id;
    private String street;
    private Integer number;
    private String location;
    private String province;

    // Constructor vacío (necesario para deserialización)
    public AddressDTO() {}

    // Constructor con parámetros
    public AddressDTO(Integer id, String street, Integer number, String location, String province) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.location = location;
        this.province = province;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", location='" + location + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}