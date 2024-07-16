package foro.hub.api.domain.topicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloIgnoreCase(String titulo);

    boolean existsByMensajeIgnoreCase(String mensaje);

    Page<Topico> findByActivoTrue(Pageable paged);
}
