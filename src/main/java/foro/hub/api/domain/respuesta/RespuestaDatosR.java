package foro.hub.api.domain.respuesta;

import java.time.LocalDateTime;

public record RespuestaDatosR(
        Long id,
        String solucion,
        Long idUsuario,
        Long idTopico,
        LocalDateTime fechaDeCreacion) {
    public RespuestaDatosR(Respuesta respuesta) {
        this(respuesta.getId(),respuesta.getSolucion(),respuesta.getAutor().getId(),respuesta.getTopico().getId(),respuesta.getFechaDeCreacion());
    }
}