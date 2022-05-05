package com.jmunoz.springbootbooksjpa.app.models;

public interface Libro {
    String getLibro();

    int getId();

    void setId(int id);

    int getUsuarioId();

    void setUsuarioId(int usuarioId);

    void setLibro(String libro);
}
