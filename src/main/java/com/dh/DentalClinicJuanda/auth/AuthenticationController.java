package com.dh.DentalClinicJuanda.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    //Necesitamos un endpoint para que los usuarios se registren, otro para que se autentiquen.
    // También necesitamos un endpoint para que los usuarios puedan actualizar su información.
    // También necesitamos un endpoint para que los usuarios puedan eliminar su cuenta.
    // También necesitamos un endpoint para que los usuarios puedan cerrar sesión.
    // También necesitamos un endpoint para que los usuarios puedan recuperar su contraseña.
    // También necesitamos un endpoint para que los usuarios puedan cambiar su contraseña.

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login (
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
