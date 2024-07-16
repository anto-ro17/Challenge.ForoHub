package foro.hub.api.domain.respuesta;

import foro.hub.api.domain.topicos.Topico;
import foro.hub.api.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name="Respuesta")
@Table(name = "respuestas")
@Getter
@Setter
@NoArgsConstructor
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fechaDeCreacion;
    private String solucion;
    @OneToOne
    @JoinColumn(name="autor", referencedColumnName="id")
    private Usuario autor;
    @OneToOne
    @JoinColumn(name="topico", referencedColumnName="id")
    private Topico topico;
    private boolean activo;

    public Respuesta(Long id, String solucion, Usuario usuario, Topico topico, LocalDateTime fechaDeCreacion) {
        this.id=id;
        this.solucion = solucion;
        this.autor = usuario;
        this.topico = topico;
        this.fechaDeCreacion = fechaDeCreacion.now();
    }

    public void actulizarRespuesta(RespuestaDatosActualizar respuestaDatosActualizar) {
        if (respuestaDatosActualizar.solucion() != null){
            this.solucion = respuestaDatosActualizar.solucion();
        }
    }
    public void desactivarRespuesta(){
        this.activo =false;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public String getSolucion() {
        return solucion;
    }

    public Usuario getAutor() {
        return autor;
    }

    public Topico getTopico() {
        return topico;
    }

    public boolean isActivo() {
        return activo;
    }
}
