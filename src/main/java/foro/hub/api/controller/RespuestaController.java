package foro.hub.api.controller;

import foro.hub.api.domain.respuesta.*;
import foro.hub.api.domain.topicos.TopicoRepository;
import foro.hub.api.domain.usuarios.UsuarioRepository;
import foro.hub.api.infra.errores.ExceptionPersonalizada;
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
@ResponseBody
@RequestMapping("/respuestas")
@SecurityRequirement(name="bearer-key")
public class RespuestaController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RespuestaServicio respuestaServicio;
    @Autowired
    private RespuestaRepository respuestaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarRespuesta (@RequestBody @Valid RespuestaDatos responseData) throws ExceptionPersonalizada {
        var finaldata = respuestaServicio.createResponse(responseData);
        return ResponseEntity.ok(finaldata);
    }

    @GetMapping
    public ResponseEntity<Page<RespuestasDatosListado>>  mostarListaDeRespuestas(@PageableDefault(size = 10) Pageable paged){
        return ResponseEntity.ok(respuestaRepository.findByActivoTrue(paged).map(RespuestasDatosListado::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarRespuesta(@RequestBody @Valid RespuestaDatosActualizar respuestaDatosActualizar){
        Respuesta respuesta = respuestaRepository.getReferenceById(respuestaDatosActualizar.id());
        respuesta.actulizarRespuesta(respuestaDatosActualizar);
        return ResponseEntity.ok(new RespuestaDatosR(respuesta.getId(),respuesta.getSolucion(),
                respuesta.getAutor().getId(), respuesta.getTopico().getId(), respuesta.getFechaDeCreacion()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarRespuesta(@PathVariable Long id){
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuesta.desactivarRespuesta();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity <RespuestaDatosR> mostrarRespuesta(@PathVariable Long id){
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        var respuestaBuscada = new RespuestaDatosR(respuesta.getId(), respuesta.getSolucion(), respuesta.getAutor().getId(),
                respuesta.getTopico().getId(), respuesta.getFechaDeCreacion());
        return ResponseEntity.ok(respuestaBuscada);
    }

}
