package api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// CAMADA: Config
// RESPONSABILIDADE: informar título, versão e descrição ao Swagger da API CulturaPass.
// SITUAÇÃO: implementada.
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI apiDoProjeto() {
        return new OpenAPI().info(new Info()
            .title("CulturaPass - Eventos e Ingressos")
            .version("Etapa 1")
            .description("API REST acadêmica SATC: eventos, clientes e compras simuladas de ingressos."));
    }
}
