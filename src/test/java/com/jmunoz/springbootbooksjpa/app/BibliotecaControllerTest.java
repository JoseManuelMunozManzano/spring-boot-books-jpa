package com.jmunoz.springbootbooksjpa.app;

import com.jmunoz.springbootbooksjpa.app.service.UsuarioYLibroService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class BibliotecaControllerTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UsuarioYLibroService usuarioYLibroServiceMock;

    @BeforeEach
    void setUp() {
        jdbc.execute("INSERT INTO usuario(id, nombre, apellidos, email) " +
                "VALUES(1, 'José Manuel', 'Muñoz Manzano', 'jmunoz@gmail.com')");
    }

    @AfterEach
    void tearDown() {
        jdbc.execute("DELETE FROM usuario");
    }
}
