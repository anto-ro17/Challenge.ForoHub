package foro.hub.api.domain.topicos;

import java.time.LocalDateTime;

public record TopicoDatosListado(
        Long id,
        String titulo,
        String mensaje,
        Estado estado,
        Long idUsuario,
        String corso,
        LocalDateTime fecha) {

    public TopicoDatosListado (Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getEstado(),topico.getAutor().getId(), topico.getCurso(),topico.getFecha());

    }
}

