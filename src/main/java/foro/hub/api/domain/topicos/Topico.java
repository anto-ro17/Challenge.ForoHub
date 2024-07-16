package foro.hub.api.domain.topicos;

import foro.hub.api.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity(name= "Topico")
@Table(name="topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "author_id")
    private Usuario autor;
    private String curso;
    private boolean activo;

    public Topico(Long id, String titulo, String mensaje, LocalDateTime date, Estado estado, Usuario usuario, String curso) {
        this.id=id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fecha =LocalDateTime.now();
        this.estado=estado;
        this.autor = usuario;
        this.curso = curso;

    }

    public void actualizarDatos(TopicoDatosActualizar topicoDatosActualizar) {
        if (topicoDatosActualizar.titulo() !=null){
            this.titulo = topicoDatosActualizar.titulo();
        }
        if (topicoDatosActualizar.mensaje() != null){
            this.mensaje=topicoDatosActualizar.mensaje();
        }
        if (topicoDatosActualizar.estado() != null){
            this.estado=topicoDatosActualizar.estado();
        }
        if (topicoDatosActualizar.curso() != null){
            this.curso=topicoDatosActualizar.curso();
        }
    }

    public void desactivarTopico(){
        this.activo=false;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public Usuario getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }

    public boolean isActivo() {
        return activo;
    }
}
