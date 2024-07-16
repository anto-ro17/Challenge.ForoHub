package foro.hub.api.domain.usuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Entity(name= "Usuario")
@Table(name="usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(unique = true)
    private String email;
    private String login;
    private String contrasena;
    private boolean activo;

    public Usuario(UsuarioDatosRegistro usuarioDatosRegistro) {
        this.nombre=usuarioDatosRegistro.nombre();
        this.email=usuarioDatosRegistro.email();
        this.login=usuarioDatosRegistro.login();
        this.contrasena=usuarioDatosRegistro.contrasena();
    }

    public Usuario(UsuarioDatosRegistro dataRegister, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.nombre=dataRegister.nombre();
        this.email=dataRegister.email();
        this.login=dataRegister.login();
        this.contrasena= bCryptPasswordEncoder.encode(dataRegister.contrasena());
    }

    public void actualizarDatos (UsuarioDatosActualizacion userDataUpdate){
        if (userDataUpdate.nombre() != null){
            this.nombre=userDataUpdate.nombre();
        }
        if (userDataUpdate.email() != null){
            this.email=userDataUpdate.email();
        }

    }

    public void desactivarUsuario(){
        this.activo=false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getContrasena() {
        return contrasena;
    }

    public boolean isActive() {
        return activo;
    }
}