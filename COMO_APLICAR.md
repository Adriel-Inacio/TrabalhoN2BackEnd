# Como aplicar este complemento ao template oficial (Windows/PowerShell)

Este ZIP contém **somente** os arquivos específicos do CulturaPass. Ele **não substitui** o template e não contém um `pom.xml` ou Maven Wrapper inventado. Use o template original do professor da Aula 10-11.

1. Clone ou atualize o repositório do professor:

```powershell
git clone https://github.com/placidodaniel/backend-aulas-satc.git
```

Se já tiver o repositório, entre nele e execute `git pull` em vez de clonar outra vez.

2. Estando na pasta **acima** de `backend-aulas-satc`, copie o template para uma nova pasta:

```powershell
Copy-Item -Recurse "backend-aulas-satc\Aula 10-11\template-projeto-final" "culturapass"
```

3. Extraia `culturapass-complemento.zip` em uma pasta temporária. Copie **o conteúdo da pasta** `culturapass-complemento` para dentro de `culturapass`, substituindo os arquivos com nomes iguais. A pasta final deve ter **um único** `src/`, `docs/` e `README.md` na raiz, ao lado de `pom.xml`, `mvnw`, `mvnw.cmd` e `docker-compose.yml`.

4. No `pom.xml` herdado do template, atualize apenas estes identificadores, preservando as versões/dependências do professor:

```xml
<groupId>br.edu.satc</groupId>
<artifactId>culturapass</artifactId>
```

Atenção: mude **somente** o `groupId` e o `artifactId` do projeto, NÃO os de dependências ou do `spring-boot-starter-parent`. O `groupId` é uma escolha técnica sugerida, não exigência do professor.

5. No `docker-compose.yml`, mude `name: projeto-final` para `name: culturapass` e `container_name: projeto-final-postgres` para `container_name: culturapass-postgres`. **Mantenha** o banco/usuário/senha `projeto` e a porta `5437:5432`, para baterem com `application.properties` original.

6. Preencha os **três nomes e links GitHub** em `README.md` antes da entrega.

7. Dentro de `culturapass`, com Docker Desktop iniciado e JDK **25** instalado:

```powershell
docker compose up -d
.\mvnw.cmd spring-boot:run
```

Linux/Mac: `./mvnw spring-boot:run`. Após subir, abra `http://localhost:8080/swagger-ui.html` e execute os cenários de `docs/testes-postman.md`.

8. Só depois dos testes, ajuste `docs/autoavaliacao.md` de acordo com o que realmente passou. O professor vai clonar a branch `main` e seguir o README.

**Não use `docker compose down -v` se quiser preservar os dados:** `-v` apaga o volume do PostgreSQL.
