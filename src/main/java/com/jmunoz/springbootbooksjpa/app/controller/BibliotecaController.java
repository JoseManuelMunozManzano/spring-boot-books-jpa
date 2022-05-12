package com.jmunoz.springbootbooksjpa.app.controller;

import com.jmunoz.springbootbooksjpa.app.models.Biblioteca;
import com.jmunoz.springbootbooksjpa.app.models.UsuarioComprador;
import com.jmunoz.springbootbooksjpa.app.service.UsuarioYLibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "informacionUsuario";
    }
}
