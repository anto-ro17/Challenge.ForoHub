package foro.hub.api.domain.topicos;

import java.time.LocalDateTime;

public record TopicoDatosRespuesta(
        Long id,
        String titulo,
        String mensaje,
        Estado estado,
        Long idUsuario,
        String curso,
        LocalDateTime fecha) {

    public TopicoDatosRespuesta(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getEstado(), topico.getAutor().getId(), topico.getCurso(), topico.getFecha());
    }
}
