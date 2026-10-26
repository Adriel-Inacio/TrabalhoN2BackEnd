# Guia de estudo e roteiro de apresentação (até 10 minutos)

## Como funciona uma compra

`POST /ingressos` com `{ "eventoId": 1, "clienteId": 1 }`:

1. **Controller (`IngressoController`)**: recebe o JSON e faz `@Valid` verificar os IDs. Chama `service.comprar()`.
2. **Service (`IngressoService`)**: abre uma transação, bloqueia o evento com `buscarParaCompra`, busca o cliente e aplica R2 → R1 → R3. Se houver violação lança `RegraDeNegocioException`.
3. **Repository (`EventoRepository`, `ClienteRepository`, `IngressoRepository`)**: lê entidades, conta ingressos e grava a nova compra.
4. **Mapper (`IngressoMapper`)**: transforma as entidades num `IngressoResponseDTO` sem levar a entidade JPA ao Controller.
5. **Banco (PostgreSQL)**: guarda um registro na tabela `ingressos` apontando para as tabelas `eventos` e `clientes`.
6. **Resposta**: `201 Created` com JSON da compra. Se uma regra for violada, `ApiExceptionHandler` devolve `400` no padrão do professor.

## Como explicar cada camada

| Arquivo | Por que existe | Como testar |
|---|---|---|
| `model/Evento.java` | JPA relaciona classe e tabela | Subir API e verificar `ddl-auto=validate` |
| `dto/EventoRequestDTO.java` | Valida JSON antes do cadastro | POST com capacidade 0 → 400 |
| `mapper/EventoMapper.java` | Traduz DTO ↔ entidade | POST /eventos → resposta tem `id` |
| `repository/EventoRepository.java` | Acessa e bloqueia o registro no banco | Compras simultâneas de um evento de 1 vaga |
| `service/IngressoService.java` | Decide se a compra é permitida | Testes R1, R2 e R3 |
| `controller/IngressoController.java` | Declara rota HTTP e status de saída | POST /ingressos → 201 ou 400 |
| `V3__criar_ingressos.sql` | Cria a tabela com FKs via Flyway | Logs de startup e consultas no banco |

## Roteiro para três integrantes

| Tempo | Integrante | O que mostrar |
|---|---|---|
| 0:00–2:00 | 1 | README: problema cultural, público e 3 regras |
| 2:00–4:00 | 2 | Diagrama ER: três tabelas, duas FKs e migrations |
| 4:00–5:00 | 2 | Contrato: rotas implementadas e planejadas |
| 5:00–7:00 | 3 | Caminho Controller → Service → Mapper/Repository → banco; abrir `IngressoService` |
| 7:00–10:00 | 1, 2 e 3 | Postman: evento 201, evento inválido 400, ID ausente 404, compra e rejeição R1 (mostrar R2/R3 se couber) |

## Perguntas prováveis

**Por que `Ingresso` é entidade e não campo de `Evento`?** Porque cada compra tem ID, data e cliente; um evento tem muitas compras, e a quantidade disponível pode ser calculada a partir dessas linhas.

**Por que as regras ficam no Service?** Para concentrar decisões de negócio independentemente do HTTP e do banco. Controller só recebe/devolve DTOs; Repository só consulta/persiste.

**Por que Flyway e `ddl-auto=validate`?** Flyway cria a estrutura em migrations versionadas; Hibernate apenas confere se as entidades correspondem às tabelas.

**Como impedir que duas compras ultrapassem a última vaga?** O Service abre uma transação e trava a linha do evento antes de contar ingressos. Outra transação que tente comprar o mesmo evento precisa aguardar a primeira.

**Por que não existe login?** Não é exigido para a Etapa 1. IDs no Postman simulam o perfil de cliente; não há verificação real de identidade/permissão.

**Os ingressos são vendidos de verdade?** Não. A operação registra aquisições simuladas no banco, sem pagamento externo.
