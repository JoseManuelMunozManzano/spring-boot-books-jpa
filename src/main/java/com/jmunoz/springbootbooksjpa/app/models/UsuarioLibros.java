package com.jmunoz.springbootbooksjpa.app.models;

import java.util.List;

public class UsuarioLibros {

    private List<Libro> libroFisicoResultados;
    private List<Libro> libroKindleResultados;
    private List<Libro> libroWebResultados;

    public UsuarioLibros() {
    }

    public int addLibroResultsParaUnaClase(List<Libro> libros) {
        return libros.size();
    }

    public List<Libro> getLibroFisicoResultados() {
        return libroFisicoResultados;
    }

    public void setLibroFisicoResultados(List<Libro> libroFisicoResultados) {
        this.libroFisicoResultados = libroFisicoResultados;
    }

    public List<Libro> getLibroKindleResultados() {
        return libroKindleResultados;
    }

    public void setLibroKindleResultados(List<Libro> libroKindleResultados) {
        this.libroKindleResultados = libroKindleResultados;
    }

    public List<Libro> getLibroWebResultados() {
        return libroWebResultados;
    }

    public void setLibroWebResultados(List<Libro> libroWebResultados) {
        this.libroWebResultados = libroWebResultados;
    }

    @Override
    public String toString() {
        return "UsuarioLibros{" +
                "libroFisicoResultados=" + libroFisicoResultados +
                ", libroKindleResultados=" + libroKindleResultados +
                ", libroWebResultados=" + libroWebResultados +
                '}';
    }
}
