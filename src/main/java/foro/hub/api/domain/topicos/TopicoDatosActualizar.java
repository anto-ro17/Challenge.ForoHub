package foro.hub.api.domain.topicos;

import jakarta.validation.constraints.NotNull;

public record TopicoDatosActualizar (
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        Estado estado,
        @NotNull
        Long idUsuario,
        String curso
){
}
