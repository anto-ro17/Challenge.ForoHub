package foro.hub.api.controller;

import foro.hub.api.domain.usuarios.UsuarioDatos;
import foro.hub.api.infra.security.TokenService;
import jakarta.validation.Valid;
import foro.hub.api.domain.usuarios.Usuario;
import foro.hub.api.infra.security.DatosJWTtoken;
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
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity realizarLogin(@RequestBody @Valid UsuarioDatos datos) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(datos.login(),
                datos.contrasena());
        var usuarioAutenticado =  manager.authenticate(authenticationToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
    }
}
