package com.example.bookmanagementperqara.book.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Book Management API",
                version = "1.0",
                description = "Book Management API Perqara Library"
        )
)
public class SwaggerConfig {
}
