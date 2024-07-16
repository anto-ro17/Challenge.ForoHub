package foro.hub.api.domain.usuarios;

import jakarta.validation.constraints.NotNull;

public record UsuarioDatosActualizacion (
        @NotNull
        Long id,
        String nombre,
        String email){

}
