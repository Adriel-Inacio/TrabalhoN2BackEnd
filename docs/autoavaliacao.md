# Autoavaliação — Etapa 1

## Importante

**Versão provisória:** os códigos e os documentos foram preparados, mas a aplicação não foi compilada com JDK 25 nem executada contra PostgreSQL/Docker neste ambiente. Marcas abaixo são conservadoras e **não representam nota obtida**. Atualizar cada linha **depois** de a equipe seguir `docs/testes-postman.md` e confirmar as respostas.

| Critério | Marca provisória | Evidência / pendência |
|---|---|---|
| P1 Sobe pelo README | ❌ | Pendente executar `docker compose up -d` e `./mvnw spring-boot:run` |
| P2 Camadas | ◐ | Código em `src/main/java/api`, verificar integração real no template |
| P3 Banco e entidades | ◐ | `V1` a `V3`; `Evento`, `Cliente`, `Ingresso`. Pendente `ddl-auto=validate` ao subir |
| P4 DTOs | ◐ | Requests, responses e validação. Pendente provar POST 400 |
| P5 Estrutura e comentários | ◐ | Repository/Mapper/Service/Controller, construtores e mapa do README; conferir montagem |
| P6 API simples | ◐ | `EventoController` tem POST/GET/GET id; pendente testar 201, 200, 400, 404 |
| P7 Três regras funcionam | ◐ | `IngressoService.comprar`; pendente executar testes R1, R2 e R3 |
| P8 Regras adequadas ao tema | ◐ | README explica capacidade, horário e distribuição de ingressos; confirmar demonstração |
| P9 Código igual ao desenho | ◐ | Diagrama ER, migrations e contrato; conferir após ajustes |
| P10 README | ◐ | Seções preenchidas; faltam nomes e GitHub dos três integrantes e validação do clone |

**Pontos esperados:** ainda não estimados; depende da verificação real. Metade restante da nota depende da apresentação.
