package api.dto;

import java.time.LocalDateTime;

// CAMADA: DTO
// RESPONSABILIDADE: mostrar a aquisição e os IDs relacionados, sem expor entidades JPA.
// SITUAÇÃO: implementada.
public record IngressoResponseDTO(Long id, Long eventoId, Long clienteId, LocalDateTime adquiridoEm) { }
