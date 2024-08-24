package buddiesapi.interfaces.rest

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun buddiesAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("Buddies API")
                    .description("")
                    .version("v0.0.1")
            )
    }
}
