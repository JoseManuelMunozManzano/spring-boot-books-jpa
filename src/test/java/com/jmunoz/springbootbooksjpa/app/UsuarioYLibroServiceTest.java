package com.jmunoz.springbootbooksjpa.app;

import com.jmunoz.springbootbooksjpa.app.models.LibroFisico;
import com.jmunoz.springbootbooksjpa.app.models.LibroKindle;
import com.jmunoz.springbootbooksjpa.app.models.LibroWeb;
import com.jmunoz.springbootbooksjpa.app.models.UsuarioComprador;
import com.jmunoz.springbootbooksjpa.app.repository.LibroFisicoDao;
import com.jmunoz.springbootbooksjpa.app.repository.LibroKindleDao;
import com.jmunoz.springbootbooksjpa.app.repository.LibroWebDao;
import com.jmunoz.springbootbooksjpa.app.repository.UsuarioDao;
import com.jmunoz.springbootbooksjpa.app.service.UsuarioYLibroService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Autowired
	private LibroFisicoDao libroFisicoDao;

	@Autowired
	private LibroWebDao libroWebDao;

	@Autowired
	private LibroKindleDao libroKindleDao;

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

	@Test
	void deleteUsuarioService() {
		// Confirmamos que existe id 1
		Optional<UsuarioComprador> deletedUsuarioComprador = usuarioDao.findById(1);
		assertTrue(deletedUsuarioComprador.isPresent(), "Devuelve True");

		// Se borra
		usuarioService.deleteUsuario(1);

		// Ya no podemos encontrar el id 1 porque se ha borrado
		deletedUsuarioComprador = usuarioDao.findById(1);
		assertFalse(deletedUsuarioComprador.isPresent(), "Devuelve False");
	}

	@Sql("/insertData.sql")
	@Test
	void getBibliotecaService() {
		Iterable<UsuarioComprador> iterableUsuarioComprador = usuarioService.getBiblioteca();

		List<UsuarioComprador> usuariosCompradores = new ArrayList<>();

		for (UsuarioComprador usuarioComprador: iterableUsuarioComprador) {
			usuariosCompradores.add(usuarioComprador);
		}

		assertEquals(5, usuariosCompradores.size());
	}

	@Test
	void createLibroService() {
		assertTrue(usuarioService.createLibro("Dune", 1, "Físico"));
		assertTrue(usuarioService.createLibro("Diseño de Patrones", 1, "Web"));
		assertTrue(usuarioService.createLibro("La mano izquierda de la oscuridad", 1, "Kindle"));

		Iterable<LibroFisico> librosFisicos = libroFisicoDao.findLibroByUsuarioId(1);
		Iterable<LibroWeb> librosWeb = libroWebDao.findLibroByUsuarioId(1);
		Iterable<LibroKindle> librosKindle = libroKindleDao.findLibroByUsuarioId(1);

		assertTrue(librosFisicos.iterator().hasNext(), "Usuario tiene libros físicos");
		assertTrue(librosWeb.iterator().hasNext(), "Usuario tiene libros web");
		assertTrue(librosKindle.iterator().hasNext(), "Usuario tiene libros kindle");
	}

	@AfterEach
	void tearDown() {
		jdbc.execute("DELETE FROM usuario");
	}
}
