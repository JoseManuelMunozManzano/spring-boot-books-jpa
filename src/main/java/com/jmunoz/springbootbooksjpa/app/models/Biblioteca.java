package com.jmunoz.springbootbooksjpa.app.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Biblioteca {
    private List<BibliotecaUsuarioComprador> usuarios = new ArrayList<>();

    public Biblioteca() {
    }

    public Biblioteca(List<BibliotecaUsuarioComprador> usuarios) {
        this.usuarios = usuarios;
    }

    public List<BibliotecaUsuarioComprador> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<BibliotecaUsuarioComprador> usuarios) {
        this.usuarios = usuarios;
    }
}
