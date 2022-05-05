package com.jmunoz.springbootbooksjpa.app.models;

import javax.persistence.*;

@Entity
@Table(name = "libro_kindle")
public class LibroKindle implements Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "usuario_id")
    private int usuarioId;

    @Column(name = "libro")
    private String libro;

    public LibroKindle() {
    }

    public LibroKindle(String libro) {
        this.libro = libro;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getUsuarioId() {
        return usuarioId;
    }

    @Override
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String getLibro() {
        return libro;
    }

    @Override
    public void setLibro(String libro) {
        this.libro = libro;
    }
}
