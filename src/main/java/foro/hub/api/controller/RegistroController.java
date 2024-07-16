package foro.hub.api.controller;

import foro.hub.api.domain.usuarios.Usuario;
import foro.hub.api.domain.usuarios.UsuarioDatosRegistro;
import foro.hub.api.domain.usuarios.UsuarioDatosRespuesta;
import foro.hub.api.domain.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity resgistro(@RequestBody @Valid UsuarioDatosRegistro datosRegistro, UriComponentsBuilder uriComponentsBuilder){
        try {
            Usuario usuario = usuarioRepository.save(new Usuario(datosRegistro, bCryptPasswordEncoder));
            UsuarioDatosRespuesta usuarioDatosRespuesta = new UsuarioDatosRespuesta(usuario.getId(),usuario.getNombre());
            URI url = uriComponentsBuilder.path("user/{idUser}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(url).body(usuarioDatosRespuesta);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().body("Fallo la validación " + e.getMessage());
        }
    }
}
