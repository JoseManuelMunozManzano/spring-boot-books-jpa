package com.jmunoz.springbootbooksjpa.app.controller;

import com.jmunoz.springbootbooksjpa.app.models.Biblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BibliotecaController {

    @Autowired
    private Biblioteca biblioteca;

    @GetMapping("/")
    public String getUsuarios(Model m) {
        return "index";
    }

    @GetMapping("/informacionUsuario/{id}")
    public String informacionUsuario(@PathVariable int id, Model m) {
        return "informacionUsuario";
    }
}
