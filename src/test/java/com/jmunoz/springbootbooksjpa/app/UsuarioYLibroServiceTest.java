package com.jmunoz.springbootbooksjpa.app;

import com.jmunoz.springbootbooksjpa.app.models.UsuarioComprador;
import com.jmunoz.springbootbooksjpa.app.repository.UsuarioDao;
import com.jmunoz.springbootbooksjpa.app.service.UsuarioYLibroService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application.properties")
@SpringBootTest
class UsuarioYLibroServiceTest {

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	private UsuarioYLibroService usuarioService;

	@Autowired
	private UsuarioDao usuarioDao;

	@BeforeEach
	void setUp() {
		jdbc.execute("INSERT INTO usuario(id, nombre, apellidos, email) " +
				"VALUES(1, 'José Manuel', 'Muñoz Manzano', 'jmunoz@gmail.com')");
	}

	@Test
	void createUsuarioService() {
		usuarioService.createUsuario("José Manuel", "Muñoz Manzano", "jmunoz@gmail.es");

		UsuarioComprador usuario = usuarioDao.findByEmail("jmunoz@gmail.es");

		assertEquals("jmunoz@gmail.es", usuario.getEmail(), "buscar por email");
	}

	@Test
	void isUsuarioNullCheck() {
		assertFalse(usuarioService.checkIfUsuarioIsNull(1));

		assertTrue(usuarioService.checkIfUsuarioIsNull(0));
	}

	@AfterEach
	void tearDown() {
		jdbc.execute("DELETE FROM usuario");
	}
}
