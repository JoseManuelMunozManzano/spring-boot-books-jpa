package com.jmunoz.springbootbooksjpa.app;

import com.jmunoz.springbootbooksjpa.app.models.BibliotecaUsuarioComprador;
import com.jmunoz.springbootbooksjpa.app.models.UsuarioComprador;
import com.jmunoz.springbootbooksjpa.app.service.UsuarioYLibroService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    void getUsuarioHttpRequest() throws Exception {
        UsuarioComprador usuarioUno = new BibliotecaUsuarioComprador("José", "Muñoz",
                "jmunoz@gmail.com");

        UsuarioComprador usuarioDos = new BibliotecaUsuarioComprador("Adriana", "Acosta",
                "adri@gmail.com");

        List<UsuarioComprador> usuarioCompradorList = new ArrayList<>(Arrays.asList(usuarioUno, usuarioDos));

        when(usuarioYLibroServiceMock.getBiblioteca()).thenReturn(usuarioCompradorList);

        assertIterableEquals(usuarioCompradorList, usuarioYLibroServiceMock.getBiblioteca());
    }

    @AfterEach
    void tearDown() {
        jdbc.execute("DELETE FROM usuario");
    }
}
