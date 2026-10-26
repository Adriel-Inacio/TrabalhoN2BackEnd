package api.repository;

import api.model.Evento;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// CAMADA: Repository
// RESPONSABILIDADE: persistir eventos e bloquear o evento enquanto uma compra é conferida.
// SITUAÇÃO: implementada.
public interface EventoRepository extends JpaRepository<Evento, Long> {
    // Evita que duas compras simultâneas ultrapassem a capacidade do mesmo evento.
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select e from Evento e where e.id = :id")
    Optional<Evento> buscarParaCompra(@Param("id") Long id);
}
