package foro.hub.api.controller;

import foro.hub.api.domain.usuarios.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name="bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<Page<UsuarioDatosListado>> mostrarListaDeUsuarios(@PageableDefault(size=10) Pageable paged){
        return ResponseEntity.ok(usuarioRepository.findByActiveTrue(paged).map(UsuarioDatosListado::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actulizarUsuarios (@RequestBody @Valid UsuarioDatosActualizacion usuarioDatosActualizacion){
        Usuario usuario = usuarioRepository.getReferenceById(usuarioDatosActualizacion.id());
        usuario.actualizarDatos(usuarioDatosActualizacion);
        return ResponseEntity.ok(new UsuarioDatosRespuesta(usuario.getId(),usuario.getNombre()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.desactivarUsuario();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity <UsuarioDatosRespuesta> mostrarUsuario(@PathVariable Long id){
              Usuario user = usuarioRepository.getReferenceById(id);
        var dUser = new UsuarioDatosRespuesta(user.getId(), user.getNombre());
        return ResponseEntity.ok(dUser);
    }

}
