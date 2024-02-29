package com.github.maxwelldantas.designpatternsspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Projeto Spring Boot gerado via Spring Initializr.
 * Os seguintes módulos foram selecionados:
 * - Spring Web
 * - Spring Data JPA
 * - H2 Database
 * - OpenFeign
 * - Spring Boot Actuator
 * - Spring Boot DevTools
 *
 * @author maxwelldantas
 */
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = "com.github.maxwelldantas.designpatternsspring")
public class DesignPatternsSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesignPatternsSpringApplication.class, args);
	}

}
