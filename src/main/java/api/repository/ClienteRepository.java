package api.repository;

import api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

// CAMADA: Repository
// RESPONSABILIDADE: persistir clientes e verificar e-mails já cadastrados.
// SITUAÇÃO: implementada.
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByEmailIgnoreCase(String email);
}
