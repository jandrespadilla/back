package com.back.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST - BackEnd para Manejo de venta de productos")
                        .version("0.4")
                        .contact(new Contact()
                                .name("Andres Padilla")
                                .email("jandrespadilla@gmail.com")
                                .url("https://jorgeandrespadilla.com.ar"))
                        .description("Esta API proporciona endpoints para el manejo de un sistema rudimentario de usuarios, productos, sus categorías y las ventas de los mismos"));
    }
}
