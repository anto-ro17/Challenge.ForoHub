package foro.hub.api.domain.respuesta;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RespuestaDatos(
        @NotBlank
        String solucion,
        @NotNull
        @Valid
        Long idUsuario,
        @NotNull
        @Valid
        Long idTopico,
        LocalDateTime fechaDeCreacion) {
}
