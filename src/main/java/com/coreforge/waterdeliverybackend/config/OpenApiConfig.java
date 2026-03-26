package com.coreforge.waterdeliverybackend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI waterDeliveryOpenAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server()
                                .url("https://water-delivery-backend-66082310358.us-central1.run.app")
                                .description("Production")
                ))
                .info(new Info()
                        .title("Water Delivery Backend API")
                        .description("Spring Boot backend for employee-based delivery operations")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Mats Johann Leal Rangel")
                                .url("https://www.linkedin.com/in/mats-johann-leal-rangel-b86114190"))
                        .license(new License()
                                .name("Internal Portfolio Project")))
                .externalDocs(new ExternalDocumentation()
                        .description("CoreForge Consulting")
                        .url("https://landingpage-delta27.web.app"));
    }
}