package api.controller;

import api.dto.IngressoRequestDTO;
import api.dto.IngressoResponseDTO;
import api.service.IngressoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// CAMADA: Controller
// RESPONSABILIDADE: receber a compra simulada, validar os IDs e responder 201.
// ROTAS ATENDIDAS: POST /ingressos; R1, R2 e R3 estão no IngressoService.
// SITUAÇÃO: implementada.
@Tag(name = "Ingressos")
@RestController
@RequestMapping("/ingressos")
public class IngressoController {
    private final IngressoService service;
    public IngressoController(IngressoService service) { this.service = service; }

    @Operation(summary = "Comprar um ingresso (compra simulada)")
    @PostMapping
    public ResponseEntity<IngressoResponseDTO> criar(@Valid @RequestBody IngressoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.comprar(dto));
    }
}
