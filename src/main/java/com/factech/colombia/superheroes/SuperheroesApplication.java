package com.factech.colombia.superheroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableCaching
@EnableSwagger2
public class SuperheroesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperheroesApplication.class, args);
	}

	@Bean
	public Docket superheroeApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.factech.colombia.superheroes"))
				.build()
				.apiInfo(getApiInfo())
				.useDefaultResponseMessages(false);
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Superheroes API",
				"Microservicios para la administración de superheroes",
				"1.0",
				"",
				new Contact("Julian Galeano", "", "judres03@gmail.com"),
				"",
				"",
				Collections.emptyList()
		);
	}

}
