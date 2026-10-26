package api.dto;

import java.time.LocalDateTime;

// CAMADA: DTO
// RESPONSABILIDADE: devolver os dados do evento e a disponibilidade calculada.
// SITUAÇÃO: implementada.
public record EventoResponseDTO(
    Long id, String nome, String descricao, String local,
    LocalDateTime dataHora, Integer capacidadeMaxima, Long ingressosDisponiveis
) { }
