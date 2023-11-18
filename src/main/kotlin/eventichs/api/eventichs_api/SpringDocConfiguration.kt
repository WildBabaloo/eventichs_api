package eventichs.api.eventichs_api

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringDocConfiguration {

    @Bean
    fun apiInfo(): OpenAPI{
        return OpenAPI()
            .info(
                Info()
                    .title("API de l'application mobile Eventichs")
                    .description("API d'un service de gestion d'évènement")
                    .version("1.0.0")
            )
    }
}