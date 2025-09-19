package com.offcl.competency_tracker.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Competency Tracker Service API")
                        .description("API documentation for Competency Tracker Service")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Gowtham K")
                                .email("gowthamkoffcl@gmail.com")));
    }
}
