package foro.hub.api.domain.respuesta;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record RespuestaDatosActualizar(
        @NotNull
        Long id,
        String solucion,
        @NotNull
        Long idUsuario,
        @NotNull
        Long idTopic,
        LocalDateTime fechaDeCreacion) {
}
