package pl.mroz.buddiesapi.interfaces.rest;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI buddiesAPI() {
        return new OpenAPI()
                .info(new Info().title("Buddies API")
                        .description("")
                        .version("v0.0.1")
                );
    }
}
