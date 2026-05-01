package com.xianglian.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("xianglian-web API 文档")
                        .version("1.0.0")
                        .description("相连项目后端接口文档")
                        .contact(new Contact().name("开发团队"))
                        .license(new License().name("Apache 2.0")));
    }
}
