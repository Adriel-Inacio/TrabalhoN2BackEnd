package api.service;

import api.dto.EventoRequestDTO;
import api.dto.EventoResponseDTO;
import api.mapper.EventoMapper;
import api.model.Evento;
import api.repository.EventoRepository;
import api.repository.IngressoRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// CAMADA: Service
// RESPONSABILIDADE: cadastrar, consultar eventos e calcular ingressos disponíveis.
// ROTAS ATENDIDAS: POST /eventos, GET /eventos e GET /eventos/{id}.
// SITUAÇÃO: implementada.
@Service
public class EventoService {
    private final EventoRepository repository;
    private final IngressoRepository ingressoRepository;
    private final EventoMapper mapper;

    public EventoService(EventoRepository repository, IngressoRepository ingressoRepository, EventoMapper mapper) {
        this.repository = repository;
        this.ingressoRepository = ingressoRepository;
        this.mapper = mapper;
    }

    @Transactional
    public EventoResponseDTO criar(EventoRequestDTO dto) {
        Evento criado = repository.save(mapper.toEntity(dto));
        return mapper.toResponse(criado, criado.getCapacidadeMaxima());
    }

    @Transactional(readOnly = true)
    public List<EventoResponseDTO> listarTodos() {
        return repository.findAll(Sort.by("id")).stream().map(this::responder).toList();
    }

    @Transactional(readOnly = true)
    public EventoResponseDTO buscarPorId(Long id) {
        Evento evento = repository.findById(id).orElseThrow(() -> new EventoNaoEncontradoException(id));
        return responder(evento);
    }

    private EventoResponseDTO responder(Evento evento) {
        long vendidos = ingressoRepository.countByEventoId(evento.getId());
        return mapper.toResponse(evento, evento.getCapacidadeMaxima() - vendidos);
    }
}
