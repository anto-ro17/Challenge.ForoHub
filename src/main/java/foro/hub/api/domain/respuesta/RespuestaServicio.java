package foro.hub.api.domain.respuesta;

import foro.hub.api.domain.topicos.TopicoRepository;
import foro.hub.api.domain.usuarios.UsuarioRepository;
import foro.hub.api.infra.errores.ExceptionPersonalizada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespuestaServicio {
    @Autowired
    private TopicoRepository topicRepository;
    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private RespuestaRepository repository;

    public RespuestaDatosR createResponse(RespuestaDatos respuestaDatos) {

        if (!userRepository.findById(respuestaDatos.idUsuario()).isPresent()){
            throw new ExceptionPersonalizada("El id de usuario no esta registrado en la base de datos");
        }

        if (!topicRepository.findById(respuestaDatos.idTopico()).isPresent()){
            throw new ExceptionPersonalizada("El topico no esta registrado en la base de datos");
        }
        var usuario = userRepository.findById(respuestaDatos.idUsuario()).get();
        var topico =topicRepository.findById(respuestaDatos.idTopico()).get();
        var respuestaValida = new Respuesta(null, respuestaDatos.solucion(),usuario,topico, respuestaDatos.fechaDeCreacion());
            repository.save(respuestaValida);
        return  new RespuestaDatosR(respuestaValida);
    }

}
