package foro.hub.api.domain.usuarios;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioDatos(
        @NotBlank(message = "Utilice correo como usuario")
        String login,
        @NotBlank(message = "La contrasena debe tener entre 8 y 10 caracteres")
        @Pattern(regexp = "\\d{8,10}")
        String contrasena) {
}
