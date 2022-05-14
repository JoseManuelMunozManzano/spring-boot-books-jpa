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
import org.springframework.beans.factory.annotation.Value;
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
    private UsuarioYLibroService usuarioCreateServiceMock;

    @Autowired
    private UsuarioDao usuarioDao;

    @Value("${sql.script.create.usuario}")
    private String sqlAddUsuario;

    @Value("${sql.script.create.libro.fisico}")
    private String sqlAddLibroFisico;

    @Value("${sql.script.create.libro.web}")
    private String sqlAddLibroWeb;

    @Value("${sql.script.create.libro.kindle}")
    private String sqlAddLibroKindle;

    @Value("${sql.script.delete.usuario}")
    private String sqlDeleteUsuario;

    @Value("${sql.script.delete.libro.fisico}")
    private String sqlDeleteLibroFisico;

    @Value("${sql.script.delete.libro.web}")
    private String sqlDeleteLibroWeb;

    @Value("${sql.script.delete.libro.kindle}")
    private String sqlDeleteLibroKindle;

    @BeforeAll
    static void beforeAll() {
        request = new MockHttpServletRequest();

        request.setParameter("nombre", "Adriana");
        request.setParameter("apellidos", "Acosta");
        request.setParameter("email", "adri@gmail.com");
    }

    @BeforeEach
    void setUp() {
        jdbc.execute(sqlAddUsuario);
        jdbc.execute(sqlAddLibroFisico);
        jdbc.execute(sqlAddLibroWeb);
        jdbc.execute(sqlAddLibroKindle);
    }

    @Test
    void getUsuarioHttpRequest() throws Exception {
        UsuarioComprador usuarioUno = new BibliotecaUsuarioComprador("José", "Muñoz",
                "jmunoz@gmail.com");
        UsuarioComprador usuarioDos = new BibliotecaUsuarioComprador("Adriana", "Acosta",
                "adri@gmail.com");

        List<UsuarioComprador> usuarioCompradorList = new ArrayList<>(Arrays.asList(usuarioUno, usuarioDos));
        when(usuarioCreateServiceMock.getBiblioteca()).thenReturn(usuarioCompradorList);
        assertIterableEquals(usuarioCompradorList, usuarioCreateServiceMock.getBiblioteca());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk()).andReturn();

        ModelAndView mav = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(mav, "index");
    }

    @Test
    void createUsuarioHttpRequest() throws Exception {
        UsuarioComprador usuarioUno = new UsuarioComprador("José", "Muñoz", "jmunoz@gmail.com");
        List<UsuarioComprador> usuarioCompradorList = new ArrayList<>(Arrays.asList(usuarioUno));

        when(usuarioCreateServiceMock.getBiblioteca()).thenReturn(usuarioCompradorList);

        assertIterableEquals(usuarioCompradorList, usuarioCreateServiceMock.getBiblioteca());

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

    @Test
    void deleteUsuarioHttpRequest() throws Exception {
        // Nos aseguramos que el usuario existe
        assertTrue(usuarioDao.findById(1).isPresent());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/delete/usuario/{id}", 1))
                .andExpect(status().isOk()).andReturn();

        ModelAndView mav = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(mav, "index");

        // Nos aseguramos que el usuario ha sido borrado
        assertFalse(usuarioDao.findById(1).isPresent());
    }

    @Test
    void deleteUsuarioHttpRequestErrorPage() throws Exception {
        // Intento borrar el usuario 0 que no existe
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/delete/usuario/{id}", 0))
                .andExpect(status().isOk()).andReturn();

        ModelAndView mav = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(mav,"error");
    }

    @AfterEach
    void tearDown() {
        jdbc.execute(sqlDeleteUsuario);
        jdbc.execute(sqlDeleteLibroFisico);
        jdbc.execute(sqlDeleteLibroWeb);
        jdbc.execute(sqlDeleteLibroKindle);
    }
}
