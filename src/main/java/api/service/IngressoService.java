package api.service;

import api.dto.IngressoRequestDTO;
import api.dto.IngressoResponseDTO;
import api.mapper.IngressoMapper;
import api.model.Cliente;
import api.model.Evento;
import api.repository.ClienteRepository;
import api.repository.EventoRepository;
import api.repository.IngressoRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// CAMADA: Service
// RESPONSABILIDADE: registrar e consultar ingressos, com as três regras do domínio.
// REGRAS: R1 capacidade, R2 data de início, R3 máximo de quatro por cliente/evento.
// ROTAS ATENDIDAS: POST /ingressos e GET /clientes/{id}/ingressos.
// SITUAÇÃO: implementada.
@Service
public class IngressoService {
    private static final int LIMITE_POR_CLIENTE = 4;
    private final IngressoRepository repository;
    private final EventoRepository eventoRepository;
    private final ClienteRepository clienteRepository;
    private final IngressoMapper mapper;

    public IngressoService(IngressoRepository repository, EventoRepository eventoRepository,
            ClienteRepository clienteRepository, IngressoMapper mapper) {
        this.repository = repository;
        this.eventoRepository = eventoRepository;
        this.clienteRepository = clienteRepository;
        this.mapper = mapper;
    }

    @Transactional
    public IngressoResponseDTO comprar(IngressoRequestDTO dto) {
        // Bloqueio pessimista: serializa compras do mesmo evento até o commit.
        Evento evento = eventoRepository.buscarParaCompra(dto.eventoId())
            .orElseThrow(() -> new EventoNaoEncontradoException(dto.eventoId()));
        Cliente cliente = clienteRepository.findById(dto.clienteId())
            .orElseThrow(() -> new ClienteNaoEncontradoException(dto.clienteId()));

        LocalDateTime agora = LocalDateTime.now();
        // R2: eventos já iniciados não aceitam novos ingressos.
        if (!agora.isBefore(evento.getDataHora())) {
            throw new RegraDeNegocioException("R2: não é possível comprar ingressos após o início do evento");
        }
        // R1: impedir emissão acima da capacidade máxima definida.
        if (repository.countByEventoId(evento.getId()) >= evento.getCapacidadeMaxima()) {
            throw new RegraDeNegocioException("R1: ingressos esgotados para este evento");
        }
        // R3: distribuir vagas e limitar o mesmo cliente a quatro ingressos.
        if (repository.countByEventoIdAndClienteId(evento.getId(), cliente.getId()) >= LIMITE_POR_CLIENTE) {
            throw new RegraDeNegocioException("R3: o cliente já possui 4 ingressos para este evento");
        }
        return mapper.toResponse(repository.save(mapper.toEntity(evento, cliente, agora)));
    }

    @Transactional(readOnly = true)
    public List<IngressoResponseDTO> listarPorCliente(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new ClienteNaoEncontradoException(clienteId);
        }
        return repository.findByClienteIdOrderByIdAsc(clienteId).stream().map(mapper::toResponse).toList();
    }
}
