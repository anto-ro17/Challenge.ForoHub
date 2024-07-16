package foro.hub.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import foro.hub.api.domain.usuarios.Usuario;
import foro.hub.api.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private Usuario usuario;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(usuario.getContrasena());
            return JWT.create()
                    .withIssuer("Foro Hub")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiración())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        if (token == null){
            throw new RuntimeException("Token is null");
        }
        DecodedJWT verifier = null;
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            String login = decodedJWT.getSubject();
            if (login == null) {
                throw new IllegalArgumentException("Token no valido: usuario no encontrado");
            }
            Usuario usuario = (Usuario) usuarioRepository.findByLogin(login);
            if (usuario == null) {
                throw new IllegalArgumentException("Usuario no encontrado " + login);
            }

            Algorithm algorithm = Algorithm.HMAC256(usuario.getPassword());
            verifier = JWT.require(algorithm)
                    .withIssuer("Foro Hub")
                    .build()
                    .verify(token);

            return verifier.getSubject();
        } catch (JWTVerificationException | IllegalArgumentException e) {
            System.out.println("Toke no valido: " + e.toString());
        }
        if (verifier.getSubject() == null){
            throw new RuntimeException("Verifier invalido");
        }
        return verifier.getSubject();
    }

    private Instant generarFechaExpiración(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
