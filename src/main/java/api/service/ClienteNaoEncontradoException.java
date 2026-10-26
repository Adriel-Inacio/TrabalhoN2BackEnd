package api.service;

// CAMADA: Service
// RESPONSABILIDADE: sinalizar cliente inexistente como erro 404.
// SITUAÇÃO: implementada.
public class ClienteNaoEncontradoException extends RecursoNaoEncontradoException {
    public ClienteNaoEncontradoException(Long id) { super("Cliente não encontrado: " + id); }
}
