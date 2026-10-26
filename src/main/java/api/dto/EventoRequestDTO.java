package api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

// CAMADA: DTO
// RESPONSABILIDADE: definir e validar o JSON enviado no POST /eventos.
// SITUAÇÃO: implementada.
public record EventoRequestDTO(
    @NotBlank(message = "Nome é obrigatório") @Size(max = 120) String nome,
    @NotBlank(message = "Descrição é obrigatória") @Size(max = 1000) String descricao,
    @NotBlank(message = "Local é obrigatório") @Size(max = 200) String local,
    @NotNull(message = "Data e hora são obrigatórias") LocalDateTime dataHora,
    @NotNull(message = "Capacidade é obrigatória") @Min(value = 1, message = "Capacidade deve ser pelo menos 1") Integer capacidadeMaxima
) { }
