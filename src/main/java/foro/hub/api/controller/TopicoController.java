package foro.hub.api.controller;

import foro.hub.api.domain.topicos.*;
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
@RequestMapping("/topicos")
@SecurityRequirement(name="bearer-key")
public class TopicoController {
    @Autowired
    private TopicoRepository topicRepository;
    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private TopicoServicio topicService;

    @PostMapping
    @Transactional
    public ResponseEntity publicarTopico(@RequestBody @Valid TopicoDatos topicoDatos) throws ExceptionPersonalizada {
        var post = topicService.crearTopico(topicoDatos);
        return ResponseEntity.ok(post);
    }

    @GetMapping
    public ResponseEntity<Page<TopicoDatosListado>>  mostrarListaDeTopicos(@PageableDefault(size = 10) Pageable paged){
        return ResponseEntity.ok(topicRepository.findByActivoTrue(paged).map(TopicoDatosListado::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopicos (@RequestBody @Valid TopicoDatosActualizar topicoDatosActualizar){
        Topico topico= topicRepository.getReferenceById(topicoDatosActualizar.id());
        topico.actualizarDatos(topicoDatosActualizar);
        return ResponseEntity.ok(new TopicoDatosRespuesta(topico.getId(),
                topico.getTitulo(),topico.getMensaje(),topico.getEstado(),topico.getAutor().getId(),
                topico.getCurso(),topico.getFecha()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarTopico(@PathVariable Long id){
        Topico topico= topicRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity <TopicoDatosRespuesta> mostrarTopico(@PathVariable Long id){
        Topico topico = topicRepository.getReferenceById(id);
        var topicoBuscado = new TopicoDatosRespuesta(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getEstado(),
                topico.getAutor().getId(),
                topico.getCurso(),
                topico.getFecha());
        return ResponseEntity.ok(topicoBuscado);
    }
}
