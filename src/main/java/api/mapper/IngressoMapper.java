package api.mapper;

import api.dto.IngressoResponseDTO;
import api.model.Cliente;
import api.model.Evento;
import api.model.Ingresso;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

// CAMADA: Mapper
// RESPONSABILIDADE: criar e converter ingressos; as três regras ficam no IngressoService.
// SITUAÇÃO: implementada.
@Component
public class IngressoMapper {
    public Ingresso toEntity(Evento evento, Cliente cliente, LocalDateTime adquiridoEm) {
        return new Ingresso(evento, cliente, adquiridoEm);
    }

    public IngressoResponseDTO toResponse(Ingresso ingresso) {
        return new IngressoResponseDTO(ingresso.getId(), ingresso.getEvento().getId(),
            ingresso.getCliente().getId(), ingresso.getAdquiridoEm());
    }
}
