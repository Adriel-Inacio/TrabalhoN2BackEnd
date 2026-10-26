package api.controller;

import api.dto.EventoRequestDTO;
import api.dto.EventoResponseDTO;
import api.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// CAMADA: Controller
// RESPONSABILIDADE: receber e devolver DTOs nas três rotas exigidas da API simples.
// ROTAS ATENDIDAS: POST /eventos, GET /eventos, GET /eventos/{id}.
// SITUAÇÃO: implementada.
@Tag(name = "Eventos")
@RestController
@RequestMapping("/eventos")
public class EventoController {
    private final EventoService service;
    public EventoController(EventoService service) { this.service = service; }

    @Operation(summary = "Cadastrar evento")
    @PostMapping
    public ResponseEntity<EventoResponseDTO> criar(@Valid @RequestBody EventoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @Operation(summary = "Listar eventos")
    @GetMapping
    public List<EventoResponseDTO> listar() { return service.listarTodos(); }

    @Operation(summary = "Buscar evento por ID")
    @GetMapping("/{id}")
    public EventoResponseDTO buscar(@PathVariable Long id) { return service.buscarPorId(id); }
}
