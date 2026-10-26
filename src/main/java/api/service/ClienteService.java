package api.service;

import api.dto.ClienteRequestDTO;
import api.dto.ClienteResponseDTO;
import api.mapper.ClienteMapper;
import api.repository.ClienteRepository;
import java.util.Locale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

// CAMADA: Service
// RESPONSABILIDADE: cadastrar cliente com e-mail normalizado e impedir e-mail duplicado.
// ROTAS ATENDIDAS: POST /clientes.
// SITUAÇÃO: implementada.
@Service
public class ClienteService {
    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteService(ClienteRepository repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public ClienteResponseDTO criar(ClienteRequestDTO dto) {
        String email = dto.email().trim().toLowerCase(Locale.ROOT);
        if (repository.existsByEmailIgnoreCase(email)) {
            throw new RegraDeNegocioException("Este e-mail já está cadastrado");
        }
        return mapper.toResponse(repository.save(mapper.toEntity(dto, email)));
    }

    @Transactional
    public ClienteResponseDTO buscarPorId(Long id) {
        return repository.findById(id)
            .map(mapper::toResponse)
            .orElseThrow(() -> new ClienteNaoEncontradoException(id));
    }

    public List<ClienteResponseDTO> listar(){
        return repository.findAll()
            .stream()
            .map(mapper::toResponse)
            .toList();
    }
}
