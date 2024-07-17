package com.rodrigoc.ForoHub.Challenge.Controller;

import com.rodrigoc.ForoHub.Challenge.Domain.Usuarios.DatosAutenticacion;
import com.rodrigoc.ForoHub.Challenge.Domain.Usuarios.Usuario;
import com.rodrigoc.ForoHub.Challenge.Infra.Security.DatosJwtToken;
import com.rodrigoc.ForoHub.Challenge.Infra.Security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionLogin {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping()
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacion datosAutenticacion){
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacion.login(),
                datosAutenticacion.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var jwtToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJwtToken(jwtToken));
    }
}
