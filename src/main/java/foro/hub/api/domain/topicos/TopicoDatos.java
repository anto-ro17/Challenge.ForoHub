package foro.hub.api.domain.topicos;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record TopicoDatos (
        @NotNull(message = "El titulo no puede repetirse")
        String titulo,
        @NotNull(message = "Usa lenguaje apropiado. El mensaje no debe superar los 700 caracteres")
        String mensaje,
        @NotNull(message = "Elija un estado´ABIERTO´ o ´CERRADO´")
        Estado estado,
        @NotNull(message = "Usa tu Autor idUsuario")
        Long idUsuario,
        @NotNull(message = "Recuerda colocar el curso adecuado para tu topico")
        String curso,
        LocalDateTime fecha){
}
