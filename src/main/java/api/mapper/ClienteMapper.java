package api.mapper;

import api.dto.ClienteRequestDTO;
import api.dto.ClienteResponseDTO;
import api.model.Cliente;
import org.springframework.stereotype.Component;

// CAMADA: Mapper
// RESPONSABILIDADE: converter Cliente e seus DTOs, sem aplicar regras de negócio.
// SITUAÇÃO: implementada.
@Component
public class ClienteMapper {
    public Cliente toEntity(ClienteRequestDTO dto, String emailNormalizado) {
        return new Cliente(dto.nome().trim(), emailNormalizado);
    }

    public ClienteResponseDTO toResponse(Cliente cliente) {
        return new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getEmail());
    }
}
