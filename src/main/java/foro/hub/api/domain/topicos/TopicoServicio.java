package foro.hub.api.domain.topicos;

import foro.hub.api.domain.usuarios.UsuarioRepository;
import foro.hub.api.infra.errores.ExceptionPersonalizada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoServicio {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public TopicoDatosRespuesta crearTopico(TopicoDatos topicoDatos) {

        if (!usuarioRepository.findById(topicoDatos.idUsuario()).isPresent()) {
            throw new ExceptionPersonalizada("Usuario no Registrado");
        }

        var titulo = topicoDatos.titulo();
        if (titulo != null && topicoRepository.existsByTituloIgnoreCase(titulo)) {
            throw new ExceptionPersonalizada("El titulo ya existe en la base de datos");
        }

        String mensaje = topicoDatos.mensaje();
        if (mensaje != null && topicoRepository.existsByMensajeIgnoreCase(mensaje)) {
            throw new ExceptionPersonalizada("El mensaje ya existe en la base de datos");
        }

        var usuario = usuarioRepository.findById(topicoDatos.idUsuario()).get();
        var topicoValido= new Topico (null,titulo,mensaje,topicoDatos.fecha(),topicoDatos.estado(),usuario,topicoDatos.curso());
        topicoRepository.save(topicoValido);
        return new TopicoDatosRespuesta(topicoValido);
    }
}
