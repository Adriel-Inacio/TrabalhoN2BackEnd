package api.dto;

// CAMADA: DTO
// RESPONSABILIDADE: devolver apenas os dados públicos do cadastro de cliente.
// SITUAÇÃO: implementada.
public record ClienteResponseDTO(Long id, String nome, String email) { }
