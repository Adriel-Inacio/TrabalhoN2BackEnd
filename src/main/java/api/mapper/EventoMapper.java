package api.mapper;

import api.dto.EventoRequestDTO;
import api.dto.EventoResponseDTO;
import api.model.Evento;
import org.springframework.stereotype.Component;

// CAMADA: Mapper
// RESPONSABILIDADE: converter a entidade Evento para os DTOs e vice-versa.
// SITUAÇÃO: implementada.
@Component
public class EventoMapper {
    public Evento toEntity(EventoRequestDTO dto) {
        return new Evento(dto.nome(), dto.descricao(), dto.local(), dto.dataHora(), dto.capacidadeMaxima());
    }

    public EventoResponseDTO toResponse(Evento evento, long ingressosDisponiveis) {
        return new EventoResponseDTO(evento.getId(), evento.getNome(), evento.getDescricao(),
            evento.getLocal(), evento.getDataHora(), evento.getCapacidadeMaxima(), ingressosDisponiveis);
    }
}
