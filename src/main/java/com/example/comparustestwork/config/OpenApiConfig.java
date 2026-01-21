package com.example.comparustestwork.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Aggregation API")
                        .version("1.0.0")
                        .description("Service for aggregating users data from multiple databases")
                        .contact(new Contact()
                                .name("Developer")
                                .email("dev@example.com")))
                .schema("ErrorResponse", new Schema<>()
                        .type("object")
                        .properties(Map.of(
                                "status", new StringSchema().example("Database error"),
                                "message", new StringSchema().example("Connection refused")
                        )));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/users/**")
                .build();
    }
}