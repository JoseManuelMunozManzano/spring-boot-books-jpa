package com.jmunoz.springbootbooksjpa.app;

import com.jmunoz.springbootbooksjpa.app.models.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class SpringBootBooksJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBooksJpaApplication.class, args);
	}

	@Bean
	@Scope(value = "prototype")
	UsuarioComprador getUsuarioComprador() {
		return new UsuarioComprador();
	}

	@Bean
	@Scope(value = "prototype")
	Libro getLibroFisico(String libro) {
		return new LibroFisico(libro);
	}

	@Bean
	@Scope(value = "prototype")
	@Qualifier("libroFisico")
	LibroFisico getLibro() {
		return new LibroFisico();
	}

	@Bean
	@Scope(value = "prototype")
	@Qualifier("libroKindle")
	LibroKindle getLibroKindle() {
		return new LibroKindle();
	}

	@Bean
	@Scope(value = "prototype")
	@Qualifier("libroWeb")
	LibroWeb getLibroWeb() {
		return new LibroWeb();
	}
}
