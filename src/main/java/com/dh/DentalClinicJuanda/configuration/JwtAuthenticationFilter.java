package com.dh.DentalClinicJuanda.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.dh.DentalClinicJuanda.entity.User;

import java.io.IOException;
@Component //Para que Spring lo pueda inyectar
@RequiredArgsConstructor //Para que Spring inyecte las dependencias (JwtService)
public class JwtAuthenticationFilter extends OncePerRequestFilter { //Queremos interceptar cada solicitud que está llegando a nuestra aplicación
    private JwtService jwtService; //Servicio que vamos a utilizar para validar el token
    private final UserDetailsService userDetailsService; //Servicio que vamos a utilizar para cargar el usuario

    @Override
    protected void doFilterInternal(
            @NonNull //Es de lombok
            HttpServletRequest request, //Petición que está llegando
            @NonNull HttpServletResponse response, //Respuesta que vamos a dar
            @NonNull FilterChain filterChain) //Encadenamiento de filtros, sistema de diseño de cadena de responsabilidades
        // que permite a un objeto pasar una solicitud a una cadena de objetos
            throws ServletException, IOException { //Excepciones que se pueden lanzar

        final String authorizationHeader = request.getHeader("Authorization"); //Obtenemos el encabezado de autorización, variable del header que venga
                //con el nombre de Authorization
                final String jwt; //Variable que va a contener el token
                final String userEmail; //Variable que va a contener el email del usuario
                if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) { //Si no hay encabezado de autorización o no empieza con Bearer
                    filterChain.doFilter(request, response); //Pasamos la petición al siguiente filtro
                    return; //Terminamos la ejecución del método
                }
                jwt = authorizationHeader.substring(7); //Si hay encabezado de autorización, obtenemos el token
                //Ahora que tenemos el jwt, tenemos que pasarlo al userdetail server para verificar si lo tenemos en la BD
                //Si lo tenemos, entonces el usuario está autenticado y podemos seguir con la petición, lo hacemos con userEmail

                userEmail = jwtService.extractUsername(jwt); //Lo configuramos en la clase user, dijimos que getUsername iba a ser el userEmail

                if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) { //Si el userEmail no es nulo y no hay autenticación
                    final UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail); //Cargamos el usuario por su email

                    if (jwtService.isTokenValid(jwt, userDetails)) { //Si el token es válido
                        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken( //Creamos un token de autenticación
                                userDetails, null, userDetails.getAuthorities()); //Le pasamos el usuario, las credenciales y los roles
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //Le pasamos los detalles de la petición
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken); //Establecemos la autenticación
                    }
                    filterChain.doFilter(request, response); //Pasamos la petición al siguiente filtro
                }
    }
}
