package com.crisda24.market.web.config;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.crisda24.market.web.controller;"))
				.build();
	}
	
	private ApiInfo apiEndpointInfo() {
		return new ApiInfoBuilder().title("API de productos")
				.description("Servicios para la consulta de productos de un supermercado")
				.license("Apache 2.0")
				.version("1.0.0")
				.licenseUrl("http://www.apache.or/licenses/LICENSE-2.0.html")
				.build();
	}
	
	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}
}
