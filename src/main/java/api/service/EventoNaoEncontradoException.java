package api.service;

// CAMADA: Service
// RESPONSABILIDADE: sinalizar que o ID de um evento não existe; o handler converte para 404.
// SITUAÇÃO: implementada.
public class EventoNaoEncontradoException extends RecursoNaoEncontradoException {
    public EventoNaoEncontradoException(Long id) { super("Evento não encontrado: " + id); }
}
