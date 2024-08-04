package com.dh.DentalClinicJuanda.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;


@Data // Nos permite no tener que escribir los getters y setters
@Builder // Nos permite no tener que escribir el constructor
@NoArgsConstructor // Nos permite no tener que escribir el constructor vacio
@AllArgsConstructor // Nos permite no tener que escribir el constructor con todos los atributos
@Entity // Nos permite indicar que esta clase es una entidad para mapearla en bd
@Table(name = "users") // Nos permite indicar el nombre de la tabla en la bd
public class User implements UserDetails{
    @Id // Nos permite indicar que este atributo es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Nos permite indicar que este atributo es autoincrementable
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING) // Esta anotacion es para indicar que el atributo es un enum y que se va a
    // guardar como un string con el proposito de que si se cambia el orden de los enums no se pierda la relacion
    private Role role; // Role es un enum que se creo en el paquete entity para indicar el rol del usuario

    //Estos métodos nacen de la implementación de UserDetails, aparece rojo y le damos click y se aplican automáticamente
    //Sin embargo, aparecen con unos valores por defecto, que cambiamos por los que aparecen ahí
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name())); // Se crea una lista con un solo elemento que es el rol del usuario
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}