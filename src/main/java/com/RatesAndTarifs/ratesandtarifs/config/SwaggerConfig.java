package com.RatesAndTarifs.ratesandtarifs.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.models.GroupedOpenApi;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Rates and Tariffs API",
        description = "API for managing product rates and tariffs with dynamic currency support",
        version = "1.0.0",
        contact = @Contact(
            name = "Your Name",
            email = "your.email@example.com"
        )
    )
)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("rates-public")
                .pathsToMatch("/api/rates/**")
                .build();
    }
}
