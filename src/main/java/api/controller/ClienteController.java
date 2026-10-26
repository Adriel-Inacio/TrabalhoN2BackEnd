package api.controller;

import api.dto.ClienteRequestDTO;
import api.dto.ClienteResponseDTO;
import api.dto.IngressoResponseDTO;
import api.service.ClienteService;
import api.service.IngressoService;
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
// RESPONSABILIDADE: cadastrar cliente e consultar seus ingressos por meio de DTOs.
// ROTAS ATENDIDAS: POST /clientes e GET /clientes/{id}/ingressos.
// SITUAÇÃO: implementada.
@Tag(name = "Clientes")
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService service;
    private final IngressoService ingressoService;

    public ClienteController(ClienteService service, IngressoService ingressoService) {
        this.service = service;
        this.ingressoService = ingressoService;
    }

    @Operation(summary = "Cadastrar cliente")
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(@Valid @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @Operation(summary = "Listar ingressos adquiridos pelo cliente")
    @GetMapping("/{id}/ingressos")
    public List<IngressoResponseDTO> ingressos(@PathVariable Long id) {
        return ingressoService.listarPorCliente(id);
    }

    @Operation(summary = "Listar todos os clientes")
    @GetMapping
    public List<ClienteResponseDTO> listar(){
        return service.listar();
    }

    @Operation(summary = "Buscar cliente pelo ID")
    @GetMapping("/{id}")
    public ClienteResponseDTO buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }
}
