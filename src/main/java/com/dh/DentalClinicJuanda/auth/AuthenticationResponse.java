package com.dh.DentalClinicJuanda.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data// Tiene todos los metrodos getters y setters, tostring, etc. ya implementados ademas tiene
// un constructor vacio ademas tiene un constructor con todos los atributos
// ademas tiene un constructor con todos los atributos
@Builder// Nos permite no tener que escribir el constructor
@AllArgsConstructor// Nos permite no tener que escribir el constructor con todos los atributos
@NoArgsConstructor// Nos permite no tener que escribir el constructor vacio
public class AuthenticationResponse {
    private String token;
}
