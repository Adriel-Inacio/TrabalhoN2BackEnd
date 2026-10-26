package api.repository;

import api.model.Ingresso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// CAMADA: Repository
// RESPONSABILIDADE: contar ingressos por evento e por cliente e consultar compras.
// SITUAÇÃO: implementada.
public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
    long countByEventoId(Long eventoId);
    long countByEventoIdAndClienteId(Long eventoId, Long clienteId);
    List<Ingresso> findByClienteIdOrderByIdAsc(Long clienteId);
}
