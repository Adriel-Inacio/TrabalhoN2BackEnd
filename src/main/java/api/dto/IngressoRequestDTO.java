package api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// CAMADA: DTO
// RESPONSABILIDADE: receber os IDs para adquirir um único ingresso.
// SITUAÇÃO: implementada.
public record IngressoRequestDTO(
    @NotNull(message = "eventoId é obrigatório") @Positive Long eventoId,
    @NotNull(message = "clienteId é obrigatório") @Positive Long clienteId
) { }
