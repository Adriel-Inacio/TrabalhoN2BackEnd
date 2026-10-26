# Contrato da API

O contrato descreve o JSON e as rotas. A implementação segue as convenções do template da SATC, sem devolver entidades JPA.

## Convenções

| Item | Valor |
|---|---|
| Base | `http://localhost:8080` |
| Formato | JSON |
| Data/hora | `yyyy-MM-dd'T'HH:mm:ss`, no horário local do servidor |
| Erros | `ErroDTO`, 400 e 404, conforme template |
| Swagger | `http://localhost:8080/swagger-ui.html` |

## Rotas

### Eventos

| Verbo | Caminho | O que faz | Entrada | Saída | Sucesso | Erros | Funciona no código |
|---|---|---|---|---|---|---|---|
| `POST` | `/eventos` | Cadastro | `EventoRequestDTO` | `EventoResponseDTO` | 201 | 400 | sim |
| `GET` | `/eventos` | Lista e disponibilidade | — | lista de `EventoResponseDTO` | 200 | — | sim |
| `GET` | `/eventos/{id}` | Buscar por ID | — | `EventoResponseDTO` | 200 | 404 | sim |
| `PUT` | `/eventos/{id}` | Editar | `EventoRequestDTO` | `EventoResponseDTO` | 200 | 400,404 | não; futura |
| `DELETE` | `/eventos/{id}` | Excluir | — | — | 204 | 400,404 | não; futura |

### Clientes

| Verbo | Caminho | O que faz | Entrada | Saída | Sucesso | Erros | Funciona no código |
|---|---|---|---|---|---|---|---|
| `POST` | `/clientes` | Cadastrar cliente | `ClienteRequestDTO` | `ClienteResponseDTO` | 201 | 400 | sim |
| `GET` | `/clientes/{id}/ingressos` | Ver ingressos do cliente | — | lista de `IngressoResponseDTO` | 200 | 404 | sim |
| `GET` | `/clientes/{id}` | Consultar cliente | — | `ClienteResponseDTO` | 200 | 404 | não; futura |

### Ingressos

| Verbo | Caminho | O que faz | Entrada | Saída | Sucesso | Erros | Funciona no código |
|---|---|---|---|---|---|---|---|
| `POST` | `/ingressos` | Simular compra de um ingresso | `IngressoRequestDTO` | `IngressoResponseDTO` | 201 | 400,404 | sim |
| `GET` | `/ingressos/{id}` | Consultar ingresso | — | `IngressoResponseDTO` | 200 | 404 | não; futura |

### Rotas que não são CRUD

| Verbo | Caminho | Objetivo | Regra | Sucesso | Erros |
|---|---|---|---|---|---|
| `POST` | `/ingressos` | Bloquear aquisição indevida | R1 capacidade, R2 prazo, R3 limite por cliente | 201 | 400,404 |
| `GET` | `/clientes/{id}/ingressos` | Mostrar ingressos já adquiridos | consulta por cliente | 200 | 404 |

## DTOs

| DTO | Direção | Campos | Validações |
|---|---|---|---|
| `EventoRequestDTO` | entrada | `nome`, `descricao`, `local`, `dataHora`, `capacidadeMaxima` | NotBlank, Size, NotNull, Min(1) |
| `EventoResponseDTO` | saída | campos anteriores mais `id`, `ingressosDisponiveis` | — |
| `ClienteRequestDTO` | entrada | `nome`, `email` | NotBlank, Email, Size |
| `ClienteResponseDTO` | saída | `id`, `nome`, `email` | — |
| `IngressoRequestDTO` | entrada | `eventoId`, `clienteId` | NotNull, Positive |
| `IngressoResponseDTO` | saída | `id`, `eventoId`, `clienteId`, `adquiridoEm` | — |

## Exemplos

### `POST /eventos`

```json
{
  "nome": "Festival de Música",
  "descricao": "Shows regionais",
  "local": "Praça Central",
  "dataHora": "2027-12-20T20:00:00",
  "capacidadeMaxima": 2
}
```

Resposta `201`, com banco recém-criado:

```json
{
  "id": 1,
  "nome": "Festival de Música",
  "descricao": "Shows regionais",
  "local": "Praça Central",
  "dataHora": "2027-12-20T20:00:00",
  "capacidadeMaxima": 2,
  "ingressosDisponiveis": 2
}
```

### `POST /clientes`

```json
{"nome":"Ana Lima","email":"ana@example.com"}
```

Resposta `201`: `{"id":1,"nome":"Ana Lima","email":"ana@example.com"}`.

### `POST /ingressos`

```json
{"eventoId":1,"clienteId":1}
```

Resposta `201` (horário ilustrativo):

```json
{"id":1,"eventoId":1,"clienteId":1,"adquiridoEm":"2026-10-25T19:30:00"}
```

### `GET /clientes/1/ingressos`

Resposta `200`: lista dos ingressos comprados, como `[ {"id":1,"eventoId":1,"clienteId":1,"adquiridoEm":"2026-10-25T19:30:00"} ]`. Lista vazia para um cliente existente sem compras.

## Erros

O template trata todos os erros de validação e domínio via `ApiExceptionHandler` e `ErroDTO`.

```json
{
  "status": 404,
  "erro": "Not Found",
  "mensagem": "Evento não encontrado: 999",
  "caminho": "/eventos/999",
  "timestamp": "2026-10-25T19:30:00",
  "campos": []
}
```

| Status | Quando ocorre |
|---|---|
| 400 | JSON ilegível, campo inválido, e-mail já usado, ou regras R1/R2/R3 violadas |
| 404 | ID inexistente de evento ou cliente |

R1: `"R1: ingressos esgotados para este evento"`.
R2: `"R2: não é possível comprar ingressos após o início do evento"`.
R3: `"R3: o cliente já possui 4 ingressos para este evento"`.
