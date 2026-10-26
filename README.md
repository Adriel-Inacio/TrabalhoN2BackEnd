<<<<<<< HEAD
# Sistema de Compra de Ingressos

> **Este é o ponto de partida do projeto do grupo.** Apague este bloco antes de entregar.
>
> **Objetivo do exercício:** planejar e organizar um backend antes de programar tudo. O grupo cria o problema e as regras de negócio, desenha o sistema, monta a estrutura de todas as entidades, faz a API funcionar com as regras implementadas e documenta tudo neste README. O enunciado completo está em `Aula 10-11/README.md`, no repositório da disciplina.
>
> **O que fazer, na ordem:**
>
> 1. Copie esta pasta inteira para fora do repositório da disciplina, com o nome do projeto, e publique no GitHub. Os comandos estão no enunciado (seção "Como começar pelo template").
> 2. Procure por `TROQUE` no projeto (`pom.xml`, `docker-compose.yml`, `OpenApiConfig.java`) e coloque o nome do projeto.
> 3. Preencha a seção [O problema](#o-problema) deste README: o problema que o grupo criou e as regras de negócio.
> 4. Preencha `docs/modelo-de-dominio.md` e `docs/contrato-da-api.md`.
> 5. Crie as tabelas e as classes de todas as entidades. O passo a passo está em [src/main/java/api/README.md](src/main/java/api/README.md).
> 6. Faça a API simples e implemente as regras de negócio.
> 7. Termine este README: troque tudo o que está entre `{{ }}` e apague o que está marcado com *(exemplo)*. Mantenha as seções e a ordem.
> 8. Abra o repositório no GitHub, leia o README como quem nunca viu o projeto e preencha `docs/autoavaliacao.md`.
>
> Na primeira vez que a API sobe, antes de existir tabela e rota, o Flyway avisa `No migrations found` e o Swagger UI mostra `No operations defined in spec!`. É o esperado.
>
> Os exemplos marcados com *(exemplo)* são de uma biblioteca. O projeto completo dela está em `Aula 10-11/exemplo-biblioteca`.

{{Uma ou duas frases: o que o sistema faz e para quem. Quem nunca viu o projeto tem que entender o que ele é lendo só este parágrafo.}}

Projeto Final da disciplina de Backend (Engenharia de Software, SATC). **Etapa 1: arquitetura do backend.**
=======
# CulturaPass — Sistema de Compra de Ingressos

API REST desenvolvida como projeto acadêmico da disciplina de **Backend — Engenharia de Software, SATC**, dentro do tema **Entretenimento e Cultura**. O sistema permite cadastrar e consultar eventos culturais, cadastrar clientes, simular a compra de ingressos e consultar as aquisições registradas no banco de dados.

**Etapa 1 — Arquitetura do backend.** O projeto é executado localmente pelo terminal; as requisições são feitas pelo **Postman** ou **Swagger UI**. Não possui interface gráfica nem integração com pagamentos reais.
>>>>>>> etapa1-culturapass

## Índice

- [Integrantes](#integrantes)
- [O problema](#o-problema)
- [Tecnologias](#tecnologias)
- [Arquitetura](#arquitetura)
- [Entidades](#entidades)
- [Rotas](#rotas)
- [Como executar](#como-executar)
- [Situação do projeto](#situação-do-projeto)
- [Documentação](#documentação)

## Integrantes

<<<<<<< HEAD
| Nome | GitHub |
|---|---|
| Adriel Inacio Cardoso | [@Adriel-Inacio](https://github.com/Adriel-Inacio) |
| Bruno da Silva Martins | [@Br-Silva1](https://github.com/Br-Silva1) |
| Pedro Bellarmino Cardoso | [@pedrobellarminocardoso](https://github.com/PedroBellarminoCardoso) |

## O problema

**Tema sorteado:** Entretenimento e Cultura

Atualmente, muitas pessoas enfrentam dificuldades para comprar ingressos de eventos devido à falta de um sistema centralizado, o que pode causar desorganização, erros no controle de vagas e dificuldades no acompanhamento das vendas. Com a implementação do sistema, o processo de compra se tornará mais prático, organizado e confiável, melhorando a experiência dos participantes e a gestão dos eventos.

**Quem usa o sistema:**

| Quem | O que faz no sistema |
|---|---|
| **Moderador** | Gerencia ingressos e vendas |
| **Cliente** | Realiza a compra de ingressos |

**Regras de negócio.**


| # | Regra | Onde está no código | Rota que usa |
|---|---|---|---|
| R1 | Não é permitido vender mais ingressos do que a quantidade disponível para o evento | `EmprestimoService` | `POST /emprestimos` |
| R2 | Cada compra deve gerar um ingresso com código único | ... | ... |
| R3 | O cliente só pode visualizar seus próprios ingressos | ... | ... |

**Fora do escopo:** {{o que o grupo decidiu não fazer, para o projeto caber no semestre}}

## Tecnologias

| O quê | Qual |
|---|---|
| Linguagem | Java 25 |
| Framework | Spring Boot 3.5 (Web, Validation, Data JPA) |
| Banco de dados | PostgreSQL 16, em Docker |
| Migrations | Flyway |
| Documentação da API | springdoc-openapi (Swagger UI) |

## Arquitetura

A API é organizada em camadas. Uma requisição entra pelo Controller, passa pelo Service, que aplica as regras de negócio, e chega ao banco pelo Repository; a resposta faz o caminho de volta. O Mapper converte DTO em entidade e entidade em DTO.

**Mapa da estrutura.** Uma linha por entidade, todas as do modelo. Em cada célula, a classe e a situação: **pronta**, **implementada** (já tem lógica), **em parte** (só uma parte das rotas) ou **montada** (criada, ligada às outras, ainda sem lógica).

| Entidade | Migration | Model | Repository | DTOs | Mapper | Service | Controller |
|---|---|---|---|---|---|---|---|
| Livro *(exemplo)* | `V1__criar_livros.sql`, pronta | `Livro`, pronta | `LivroRepository`, pronto | `LivroRequestDTO`, `LivroResponseDTO`, prontos | `LivroMapper`, implementado | `LivroService`, implementado | `LivroController`, implementado |
| ... | ... | ... | ... | ... | ... | ... | ... |

## Entidades

| Entidade | O que representa | Como se relaciona |
|---|---|---|
| Livro *(exemplo)* | Um exemplar do acervo que pode ser emprestado | Aparece em vários empréstimos |
| ... | ... | ... |

Atributos, tipos e o diagrama das tabelas estão em [docs/modelo-de-dominio.md](docs/modelo-de-dominio.md).

## Rotas

### As que já funcionam

| Verbo | Caminho | O que faz | Regra | Sucesso | Erros |
|---|---|---|---|---|---|
| `POST` *(exemplo)* | `/livros` | Cadastra um livro | Nenhuma | `201` | `400` |
| `POST` *(exemplo)* | `/emprestimos` | Registra um empréstimo | R1 e R2 | `201` | `400`, `404` |
| ... | ... | ... | ... | ... | ... |

Exemplo de requisição *(exemplo: troque por uma rota do grupo)*:

```http
POST /livros
Content-Type: application/json

{
  "titulo": "Dom Casmurro",
  "isbn": "978-85-359-0277-5"
}
```

Resposta, `201 Created`:
=======
| Integrante | GitHub |
|---|---|
| Adriel Inacio Cardoso | [@Adriel-Inacio](https://github.com/Adriel-Inacio) |
| Bruno da Silva Martins | [@Br-Silva1](https://github.com/Br-Silva1) |
| Pedro Bellarmino Cardoso | [@pedrobellarminocardoso](https://github.com/pedrobellarminocardoso) |

## O problema

O gerenciamento manual de eventos e ingressos pode causar vendas acima da capacidade, dificuldade para verificar vagas disponíveis e falta de organização dos registros de clientes e compras. O **CulturaPass** centraliza essas informações e aplica validações antes de registrar cada ingresso, contribuindo para um controle mais confiável.

**Tema:** Entretenimento e Cultura.

**Quem usa o sistema:**

| Perfil | Responsabilidades |
|---|---|
| Funcionário / organizador | Cadastrar eventos e consultar as informações e vagas disponíveis. |
| Cliente | Consultar eventos, informar seus dados para cadastro, adquirir ingressos de forma simulada e consultar seus ingressos. |

> **Importante:** esses perfis descrevem o uso pretendido. Ainda não existe autenticação nem autorização por usuário; conhecer um ID é suficiente para consultar os dados oferecidos pelas rotas. Portanto, não se deve afirmar que um cliente só consegue visualizar os próprios ingressos.

### Regras de negócio

As três regras abaixo estão implementadas no método `comprar` de `IngressoService` na versão de código desenvolvida para a Etapa 1:

| Regra | Descrição | Classe / método | Rota |
|---|---|---|---|
| **R1 — Capacidade** | A quantidade de ingressos vendidos não pode superar a capacidade máxima cadastrada para o evento. | `IngressoService.comprar` | `POST /ingressos` |
| **R2 — Prazo** | Não é permitida a compra de ingressos de eventos cujo horário de início já chegou ou passou. | `IngressoService.comprar` | `POST /ingressos` |
| **R3 — Limite por cliente** | Cada cliente pode adquirir no máximo **quatro ingressos por evento**. | `IngressoService.comprar` | `POST /ingressos` |

Na compra, o sistema verifica a existência de evento e cliente e registra apenas um ingresso por requisição. Para evitar que compras simultâneas ultrapassem a capacidade, a implementação utiliza uma transação e bloqueio pessimista sobre o registro do evento.

**Fora do escopo da Etapa 1:** pagamento real, QR Code, emissão de comprovantes, autenticação, autorização por perfil, interface web, cancelamento de ingressos e CRUD completo de todas as entidades. As compras são **simuladas** por registros persistidos no banco.

## Tecnologias

| Componente | Tecnologia |
|---|---|
| Linguagem | Java 25 |
| Framework | Spring Boot 3.5.x |
| API HTTP | Spring Web (REST/JSON) |
| Persistência | Spring Data JPA / Hibernate |
| Banco de dados | PostgreSQL 16, iniciado com Docker Compose |
| Versionamento do banco | Flyway (migrations SQL) |
| Entrada e saída de dados | DTOs e Bean Validation |
| Documentação e exploração da API | springdoc-openapi / Swagger UI |
| Gerenciamento de dependências | Maven Wrapper (`mvnw` / `mvnw.cmd`) |
| Testes manuais das rotas | Postman |
| Versionamento do projeto | Git e GitHub |

As versões exatas das dependências são definidas em `pom.xml` e no `docker-compose.yml` da cópia local do template da disciplina.

## Arquitetura

O projeto segue a **arquitetura em camadas** trabalhada na disciplina:

```text
Postman / Swagger UI
        |
        v
Controller  -> recebe requisições e retorna status HTTP/DTOs
        |
        v
Service     -> coordena operações e aplica regras de negócio
        |
        v
Repository  -> consulta e persiste dados com Spring Data JPA
        |
        v
PostgreSQL  -> armazena eventos, clientes e ingressos
```

O **Mapper** converte entidades JPA em DTOs e vice-versa. A API não expõe diretamente as entidades do banco.

### Organização do código

```text
src/main/
├── java/api/
│   ├── config/       # Configuração da API e Swagger
│   ├── controller/   # Rotas HTTP
│   ├── dto/          # Objetos e validações de entrada/saída
│   ├── mapper/       # Conversões entre DTOs e entidades
│   ├── model/        # Entidades JPA
│   ├── repository/   # Persistência no PostgreSQL
│   └── service/      # Regras de negócio e exceções de domínio
└── resources/
    ├── application.properties
    └── db/migration/  # Scripts SQL do Flyway
```

### Mapa das três entidades

| Entidade | Migration | Model | Repository | DTOs | Mapper | Service | Controller |
|---|---|---|---|---|---|---|---|
| Evento | `V1__criar_eventos.sql` | `Evento` | `EventoRepository` | `EventoRequestDTO`, `EventoResponseDTO` | `EventoMapper` | `EventoService` | `EventoController` |
| Cliente | `V2__criar_clientes.sql` | `Cliente` | `ClienteRepository` | `ClienteRequestDTO`, `ClienteResponseDTO` | `ClienteMapper` | `ClienteService` | `ClienteController` |
| Ingresso | `V3__criar_ingressos.sql` | `Ingresso` | `IngressoRepository` | `IngressoRequestDTO`, `IngressoResponseDTO` | `IngressoMapper` | `IngressoService` | `IngressoController` |

As três entidades possuem as camadas previstas no complemento do projeto. É necessário publicar esses arquivos na branch do GitHub junto com este README.

## Entidades

| Entidade | Principais atributos | Relacionamentos |
|---|---|---|
| **Evento** | `id`, `nome`, `descricao`, `local`, `dataHora`, `capacidadeMaxima` | Um evento pode ter vários ingressos. |
| **Cliente** | `id`, `nome`, `email` | Um cliente pode possuir vários ingressos. O e-mail deve ser único. |
| **Ingresso** | `id`, `evento`, `cliente`, `adquiridoEm` | Cada ingresso pertence a um evento e a um cliente. |

O relacionamento entre `Ingresso` e `Evento`, assim como entre `Ingresso` e `Cliente`, é **muitos-para-um (`@ManyToOne`)**. As chaves estrangeiras são `evento_id` e `cliente_id` na tabela de ingressos.

A quantidade disponível é calculada pela capacidade do evento menos os ingressos já registrados, sem duplicar esse valor em uma coluna extra.

O modelo completo, com o diagrama e os tipos de cada atributo, está em [docs/modelo-de-dominio.md](docs/modelo-de-dominio.md).

## Rotas

**Endereço base:** `http://localhost:8080`. As requisições e respostas usam **JSON**.

### Rotas com implementação fornecida para a Etapa 1

| Método | Caminho | Funcionalidade | Regra | Sucesso | Erros esperados |
|---|---|---|---|---|---|
| `POST` | `/eventos` | Cadastrar evento | Validações do DTO | `201` | `400` |
| `GET` | `/eventos` | Listar eventos e disponibilidade | — | `200` | — |
| `GET` | `/eventos/{id}` | Consultar evento pelo ID | — | `200` | `404` |
| `POST` | `/clientes` | Cadastrar cliente | E-mail não duplicado | `201` | `400` |
| `POST` | `/ingressos` | Simular compra de um ingresso | R1, R2, R3 | `201` | `400`, `404` |
| `GET` | `/clientes/{id}/ingressos` | Consultar ingressos vinculados ao cliente | — | `200` | `404` |

### Consultas adicionais de clientes

Durante o desenvolvimento, foram propostas duas consultas adicionais. **Confirme se foram adicionadas ao código local antes de marcá-las como concluídas na autoavaliação ou apresentá-las como funcionais:**

| Método | Caminho | Funcionalidade | Situação |
|---|---|---|---|
| `GET` | `/clientes` | Listar clientes cadastrados | Verificar no `ClienteController` e `ClienteService` locais |
| `GET` | `/clientes/{id}` | Consultar cliente pelo ID | Verificar no `ClienteController` e `ClienteService` locais |

### Exemplos de uso no Postman

**1. Cadastrar um evento**

`POST http://localhost:8080/eventos`

```json
{
  "nome": "Festival Cultural SATC",
  "descricao": "Apresentações de música e teatro",
  "local": "Auditório SATC",
  "dataHora": "2027-12-20T19:00:00",
  "capacidadeMaxima": 2
}
```

Resposta esperada `201 Created` (IDs ilustrativos):
>>>>>>> etapa1-culturapass

```json
{
  "id": 1,
<<<<<<< HEAD
  "titulo": "Dom Casmurro",
  "isbn": "978-85-359-0277-5",
  "disponivel": true
}
```

### Erros

Todo erro sai no mesmo formato. Este é o `400` de uma regra de negócio violada *(exemplo)*:
=======
  "nome": "Festival Cultural SATC",
  "descricao": "Apresentações de música e teatro",
  "local": "Auditório SATC",
  "dataHora": "2027-12-20T19:00:00",
  "capacidadeMaxima": 2,
  "ingressosDisponiveis": 2
}
```

**2. Cadastrar um cliente**

`POST http://localhost:8080/clientes`

```json
{
  "nome": "Cliente Exemplo",
  "email": "cliente@example.com"
}
```

**3. Comprar um ingresso**

`POST http://localhost:8080/ingressos`

```json
{
  "eventoId": 1,
  "clienteId": 1
}
```

Os IDs informados devem existir no banco. A compra aceita retorna `201 Created` com o ID do ingresso, `eventoId`, `clienteId` e `adquiridoEm`.

**4. Consultar as compras de um cliente**

`GET http://localhost:8080/clientes/1/ingressos`

### Erros e validações

| Situação | Status HTTP |
|---|---|
| Cadastro ou compra bem-sucedido | `201 Created` |
| Consulta bem-sucedida | `200 OK` |
| Dados inválidos ou uma regra de negócio violada | `400 Bad Request` |
| Cliente ou evento inexistente | `404 Not Found` |

Exemplo de resposta para uma compra recusada por falta de ingressos:
>>>>>>> etapa1-culturapass

```json
{
  "status": 400,
  "erro": "Bad Request",
<<<<<<< HEAD
  "mensagem": "R2: o livro já está emprestado",
  "caminho": "/emprestimos",
  "timestamp": "2026-10-06T19:30:00",
=======
  "mensagem": "R1: ingressos esgotados para este evento",
  "caminho": "/ingressos",
  "timestamp": "2026-10-26T16:00:00",
>>>>>>> etapa1-culturapass
  "campos": []
}
```

<<<<<<< HEAD
Um campo inválido também devolve `400`, com a lista `campos` dizendo qual campo corrigir. Um id que não existe devolve `404`.

### As planejadas

Estão no contrato, mas ainda não respondem.

| Recurso | O que vai fazer |
|---|---|
| ... | ... |

O contrato completo, com todas as rotas e os DTOs, está em [docs/contrato-da-api.md](docs/contrato-da-api.md).

## Como executar

Pré-requisitos: JDK 25, Docker Desktop aberto e Git.

```bash
git clone https://github.com/{{usuario}}/{{repositorio}}.git
cd {{repositorio}}

docker compose up -d          # sobe o PostgreSQL na porta 5437
./mvnw spring-boot:run        # Windows: .\mvnw.cmd spring-boot:run
```

Com a API no ar:

| Endereço | O que é |
|---|---|
| <http://localhost:8080/swagger-ui.html> | Swagger UI: as rotas, com **Try it out** para enviar requisições |
| <http://localhost:8080/v3/api-docs> | O contrato OpenAPI em JSON |

Para testar as rotas, use o Swagger UI ou o **Postman**. No Postman, importe todas as rotas de uma vez: **Import** → **Link** → `http://localhost:8080/v3/api-docs`.

Para parar: `Ctrl+C` na API e `docker compose stop` no banco. Para apagar o banco e rodar as migrations do zero: `docker compose down -v` e suba de novo.

## Situação do projeto

| Parte | Situação |
|---|---|
| Desenho | {{ex.: completo: problema e regras neste README, modelo e contrato em docs/}} |
| Estrutura | {{ex.: as 3 entidades têm migration, entidade, Repository, DTOs, Mapper, Service e Controller}} |
| API e regras | {{ex.: as rotas de livros funcionam; R1, R2 e R3 estão implementadas}} |

**Ainda não funciona:** {{as rotas planejadas que ainda não respondem}}

## Documentação

| Documento | O que tem |
|---|---|
| [Modelo de domínio](docs/modelo-de-dominio.md) | Entidades, relacionamentos, diagrama das tabelas, migrations |
| [Contrato da API](docs/contrato-da-api.md) | Todas as rotas, os DTOs, exemplos de JSON e erros |
| [Autoavaliação](docs/autoavaliacao.md) | Os critérios da revisão do código, marcados pelo grupo |
=======
O `timestamp` é ilustrativo. O formato de erros utiliza o handler e os DTOs de erro do template da disciplina.

### Funcionalidades futuras

| Rota / recurso | Objetivo |
|---|---|
| `PUT /eventos/{id}` | Atualizar um evento |
| `DELETE /eventos/{id}` | Excluir um evento considerando as compras existentes |
| Autenticação e autorização | Garantir acesso conforme identidade e perfil |
| Pagamentos e cancelamentos | Evolução do fluxo de compra |

O detalhamento dos DTOs e das rotas está em [docs/contrato-da-api.md](docs/contrato-da-api.md).

## Como executar

### Pré-requisitos

- **JDK 25** instalado (`java -version` e `javac -version`).
- **Docker Desktop** aberto, com Docker Compose disponível.
- **Git** para clonar o repositório.
- **Postman** ou navegador para acessar o Swagger UI.

### 1. Baixar o projeto

```bash
git clone https://github.com/Adriel-Inacio/TrabalhoN2BackEnd.git
cd TrabalhoN2BackEnd
```

> **Atenção:** o código Java, `pom.xml`, `docker-compose.yml`, `mvnw`, `.mvn/`, `src/` e `docs/` precisam estar publicados na branch escolhida. Se a `main` contiver apenas este README, clonar a `main` ainda não permitirá executar a API. Para outra branch, utilize `git switch NOME_DA_BRANCH` após clonar.

### 2. Iniciar o PostgreSQL

Na pasta raiz (a que contém `docker-compose.yml`):

```bash
docker compose up -d
docker compose ps
```

Conforme a configuração de desenvolvimento do template utilizado, o banco é acessado em `localhost:5437`, com nome, usuário e senha `projeto`. Essas credenciais são apenas para execução local e devem ser verificadas nos arquivos de configuração.

### 3. Executar a aplicação

**Windows / PowerShell:**

```powershell
.\mvnw.cmd clean compile
.\mvnw.cmd spring-boot:run
```

**Linux / macOS:**

```bash
./mvnw clean compile
./mvnw spring-boot:run
```

Quando o servidor estiver inicializado corretamente, a API estará em `http://localhost:8080`. O Flyway executa as migrations pendentes na inicialização; não é necessário criar as tabelas manualmente.

### 4. Acessar e testar a API

| Endereço | Uso |
|---|---|
| `http://localhost:8080/swagger-ui.html` | Documentação interativa e testes dos endpoints |
| `http://localhost:8080/v3/api-docs` | Especificação OpenAPI em JSON |

No Postman, use requisições `GET` ou `POST` para as rotas listadas acima. Para `POST`, escolha **Body → raw → JSON**. Também é possível importar a definição da API por **Import → Link** usando `http://localhost:8080/v3/api-docs` enquanto a aplicação estiver no ar.

### 5. Encerrar e reiniciar

Pare o servidor Java com `Ctrl+C`. Para parar o banco sem apagar os dados:

```bash
docker compose stop
```

Para iniciar o banco novamente, use `docker compose up -d` e depois execute o Maven Wrapper. **Não execute `docker compose down -v` a menos que queira remover o volume do banco e perder os registros locais.**

## Situação do projeto

| Parte | Estado |
|---|---|
| Problema e regras | Definidos para o domínio de eventos e ingressos |
| Modelo de domínio | Três entidades e dois relacionamentos definidos |
| Arquitetura | Camadas, DTOs, repositórios, mappers e migrations preparados no projeto local |
| API principal | Seis rotas escritas no complemento de código |
| Consultas adicionais de clientes | Verificação pendente no código local (`GET /clientes` e `GET /clientes/{id}`) |
| Regras R1, R2 e R3 | Implementadas no código do Service; validar em execução |
| Compilação e testes finais | Dependem de execução local pela equipe e atualização da autoavaliação |
| Publicação no GitHub | Necessário enviar os arquivos do backend à branch de trabalho |

As funcionalidades descritas como implementadas referem-se ao **código preparado localmente**. A presença de código não substitui teste de compilação, conexão com PostgreSQL ou verificação no Postman.

## Documentação

| Documento | Conteúdo |
|---|---|
| [Modelo de domínio](docs/modelo-de-dominio.md) | Entidades, atributos, tipos, relacionamentos e migrations |
| [Contrato da API](docs/contrato-da-api.md) | Endpoints, DTOs, exemplos de JSON e respostas |
| [Autoavaliação](docs/autoavaliacao.md) | Análise dos critérios P1 a P10 e pendências da equipe |
| [Testes no Postman](docs/testes-postman.md) | Cenários de sucesso, capacidade, prazo e limite por cliente |

**Referência acadêmica:** [Materiais de Backend da SATC — Aulas 10 e 11](https://github.com/placidodaniel/backend-aulas-satc/tree/main/Aula%2010-11).
>>>>>>> etapa1-culturapass
