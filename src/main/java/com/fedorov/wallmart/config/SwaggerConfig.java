package com.fedorov.wallmart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fedorov.wallmart.controller"))
                .paths(postPaths())
                .build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/products/posts.*"),
                regex("/products.*"),
                regex("/categories.*"),
                regex("/producers.*"));
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Fedorov`s Wallmart API")
                .description("\"Fedorov`s Wallmart API reference for development\"")
                .termsOfServiceUrl("https://www.javainuse.com/spring/boot_swagger")
                .contact("https://www.javainuse.com/spring/boot_swagger")
                .license("Fedorov`s License: read to configure")
                .licenseUrl("https://www.javainuse.com/spring/boot_swagger").version("0.01")
                .build();
    }
}