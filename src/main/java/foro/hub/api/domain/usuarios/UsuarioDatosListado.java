package foro.hub.api.domain.usuarios;

public record UsuarioDatosListado(
        Long id,
        String nombre,
        String email) {
    public UsuarioDatosListado(Usuario usuario){
        this(usuario.getId(),usuario.getNombre(),usuario.getEmail());
    }
}
