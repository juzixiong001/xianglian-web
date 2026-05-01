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
                        .title("xianglian-web 接口文档")
                        .version("1.0.0")
                        .description("# 简介\n这是一个简洁美观的API文档工具\n\n## 功能特点\n- 在线调试接口\n- 侧边栏导航\n- 接口分组展示")
                        .contact(new Contact().name("开发团队").url("https://github.com/your-team")))
                ;
    }
}
