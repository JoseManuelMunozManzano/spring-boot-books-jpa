package com.jmunoz.springbootbooksjpa.app.controller;

import com.jmunoz.springbootbooksjpa.app.models.Biblioteca;
import com.jmunoz.springbootbooksjpa.app.models.BibliotecaUsuarioComprador;
import com.jmunoz.springbootbooksjpa.app.models.UsuarioComprador;
import com.jmunoz.springbootbooksjpa.app.service.UsuarioYLibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BibliotecaController {

    @Autowired
    private Biblioteca biblioteca;

    @Autowired
    private UsuarioYLibroService usuarioService;

    @GetMapping("/")
    public String getUsuarios(Model m) {
        Iterable<UsuarioComprador> usuariosCompradores = usuarioService.getBiblioteca();
        m.addAttribute("usuarios", usuariosCompradores);

        return "index";
    }

    @PostMapping("/")
    public String createUsuario(UsuarioComprador usuario, Model m) {
        usuarioService.createUsuario(usuario.getNombre(), usuario.getApellidos(), usuario.getEmail());

        Iterable<UsuarioComprador> usuarioCompradores = usuarioService.getBiblioteca();
        m.addAttribute("usuarios", usuarioCompradores);

        return "index";
    }

    @GetMapping("/delete/usuario/{id}")
    public String deleteUsuario(@PathVariable int id, Model m) {
        if (usuarioService.checkIfUsuarioIsNull(id)) {
            return "error";
        }

        usuarioService.deleteUsuario(id);

        Iterable<UsuarioComprador> usuarioCompradores = usuarioService.getBiblioteca();
        m.addAttribute("usuarios", usuarioCompradores);

        return "index";
    }

    @GetMapping("/informacionUsuario/{id}")
    public String informacionUsuario(@PathVariable int id, Model m) {
        if (usuarioService.checkIfUsuarioIsNull(id)) {
            return "error";
        }

        BibliotecaUsuarioComprador usuarioEntity = usuarioService.usuarioInformacion(id);

        m.addAttribute("usuario", usuarioEntity);

        return "informacionUsuario";
    }

    @PostMapping("/libros")
    public String createLibro(@RequestParam String libro, @RequestParam String tipoLibro, @RequestParam int usuarioId, Model m) {
        if (usuarioService.checkIfUsuarioIsNull(usuarioId)) {
            return "error";
        }

        boolean success = usuarioService.createLibro(libro, usuarioId, tipoLibro);
        if (!success) {
            return "error";
        }

        BibliotecaUsuarioComprador usuarioEntity = usuarioService.usuarioInformacion(usuarioId);
        m.addAttribute("usuario", usuarioEntity);

        return "informacionUsuario";
    }
}
