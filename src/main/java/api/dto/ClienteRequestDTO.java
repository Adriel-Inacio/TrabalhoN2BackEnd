package api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// CAMADA: DTO
// RESPONSABILIDADE: definir e validar o cadastro de cliente.
// SITUAÇÃO: implementada.
public record ClienteRequestDTO(
    @NotBlank(message = "Nome é obrigatório") @Size(max = 120) String nome,
    @NotBlank(message = "E-mail é obrigatório") @Email(message = "E-mail inválido")
    @Size(max = 255) String email
) { }
