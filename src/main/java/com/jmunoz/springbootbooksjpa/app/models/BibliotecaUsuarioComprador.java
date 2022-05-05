package com.jmunoz.springbootbooksjpa.app.models;

public class BibliotecaUsuarioComprador extends UsuarioComprador {

    private int id;

    private UsuarioLibros usuarioLibros;

    public BibliotecaUsuarioComprador(String nombre, String apellidos, String email) {
        super(nombre, apellidos, email);
    }

    public BibliotecaUsuarioComprador(int id, String nombre, String apellidos, String email, UsuarioLibros usuarioLibros) {
        super(nombre, apellidos, email);
        this.usuarioLibros = usuarioLibros;
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public UsuarioLibros getUsuarioLibros() {
        return usuarioLibros;
    }

    public void setUsuarioLibros(UsuarioLibros usuarioLibros) {
        this.usuarioLibros = usuarioLibros;
    }
}
