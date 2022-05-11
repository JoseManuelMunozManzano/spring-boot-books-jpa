package com.jmunoz.springbootbooksjpa.app;

import com.jmunoz.springbootbooksjpa.app.models.BibliotecaUsuarioComprador;
import com.jmunoz.springbootbooksjpa.app.models.UsuarioComprador;
import com.jmunoz.springbootbooksjpa.app.repository.UsuarioDao;
import com.jmunoz.springbootbooksjpa.app.service.UsuarioYLibroService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class BibliotecaControllerTest {

    private static MockHttpServletRequest request;

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UsuarioYLibroService usuarioYLibroServiceMock;

    @Autowired
    private UsuarioDao usuarioDao;

    @BeforeAll
    static void beforeAll() {
        request = new MockHttpServletRequest();

        request.setParameter("nombre", "Adriana");
        request.setParameter("apellidos", "Acosta");
        request.setParameter("email", "adri@gmail.com");
    }

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

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk()).andReturn();

        ModelAndView mav = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(mav, "index");
    }

    @Test
    void createUsuarioHttpRequest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .param("nombre", request.getParameterValues("nombre"))
                .param("apellidos", request.getParameterValues("apellidos"))
                .param("email", request.getParameterValues("email"))
        ).andExpect(status().isOk()).andReturn();

        ModelAndView mav = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(mav, "index");

        UsuarioComprador verifyUsuario = usuarioDao.findByEmail("adri@gmail.com");
        assertNotNull(verifyUsuario, "Usuario debe encontrarse");
    }

    @AfterEach
    void tearDown() {
        jdbc.execute("DELETE FROM usuario");
    }
}
