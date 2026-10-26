# Testes no Postman — sequência para o grupo

**Pré-requisito:** aplicar o complemento sobre o template oficial, iniciar Docker e API. Postman → Import → Link → `http://localhost:8080/v3/api-docs` ou criar requisições HTTP manualmente. Nos POSTs use `Body > raw > JSON`.

As IDs abaixo são exemplos de um banco limpo. Use os IDs **reais** que cada resposta retornar. O horário dos exemplos futuros considera 2026-10-25 como data de elaboração.

## Caso A — API simples

**1. POST** `http://localhost:8080/eventos`

```json
{"nome":"Festival do Centro","descricao":"Música e teatro","local":"Praça Central","dataHora":"2027-12-20T20:00:00","capacidadeMaxima":2}
```

Esperado: `201` e `id` novo, `ingressosDisponiveis:2`.

**2. GET** `http://localhost:8080/eventos` → `200` e lista com o evento.

**3. GET** `http://localhost:8080/eventos/1` → `200` com o evento (troque ID).

**4. GET** `http://localhost:8080/eventos/999999` → `404`, mensagem `Evento não encontrado: 999999`.

**5. POST** `/eventos` com `capacidadeMaxima:0` → `400` e campo `capacidadeMaxima` na lista `campos` (mantenha os demais campos válidos).

## Caso B — compra bem-sucedida e R1

**6. POST** `http://localhost:8080/clientes`

```json
{"nome":"Ana Lima","email":"ana@example.com"}
```

Esperado: `201`, anote `clienteId` retornado.

**7. POST** `http://localhost:8080/ingressos` duas vezes:

```json
{"eventoId":1,"clienteId":1}
```

Esperado: ambos `201` em evento de capacidade 2.

**8. POST** `/ingressos` terceira vez com os mesmos IDs → `400` e mensagem começando com `R1:`.

**9. GET** `http://localhost:8080/clientes/1/ingressos` → `200`, lista com dois ingressos.

**10. GET** `/eventos/1` → `200` e `ingressosDisponiveis: 0`.

## Caso C — R2: evento já iniciado

**11. POST** `/eventos` com data no passado:

```json
{"nome":"Show Encerrado","descricao":"Evento de teste da regra R2","local":"Teatro","dataHora":"2020-01-01T20:00:00","capacidadeMaxima":20}
```

Anote o ID criado. **POST** `/ingressos` com esse ID e um cliente existente → `400`, mensagem `R2: não é possível comprar ingressos após o início do evento`.

## Caso D — R3: máximo de quatro ingressos

**12. POST** `/eventos` com capacidade 10, no futuro:

```json
{"nome":"Mostra Cultural","descricao":"Teste do limite por cliente","local":"Auditório","dataHora":"2027-12-21T19:00:00","capacidadeMaxima":10}
```

Anote o novo `eventoId`. Envie **POST** `/ingressos` quatro vezes para **o mesmo cliente e esse evento**. Os quatro devem retornar `201`. O **quinto** deve retornar `400`, mensagem começando com `R3:`.

## Caso E — integridade e persistência

**13. POST** `/ingressos` com `eventoId:999999` ou `clienteId:999999` → `404`.

**14. Pare a API** (`Ctrl+C`), pare o banco (`docker compose stop`), depois inicie o banco (`docker compose start`) e a API de novo. Faça `GET /eventos` e `GET /clientes/1/ingressos` e confirme que os dados continuam presentes.

**15. Teste opcional de concorrência:** dois POST simultâneos para um evento com somente um lugar devem resultar em exatamente um sucesso e uma regra R1 violada. O código protege isso com lock pessimista no registro do evento, dentro de transação.

### Se um teste falhar

Confira se está executando JDK 25, se a porta 5437 está livre, se Flyway executou V1/V2/V3 e se o `pom.xml` original foi preservado. Não altere uma migration já aplicada: crie nova V4 ou, **apenas se for banco descartável de testes**, recrie o volume.
