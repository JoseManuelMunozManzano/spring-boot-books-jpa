package com.jmunoz.springbootbooksjpa.app.models;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class UsuarioComprador implements Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @Column
    private String apellidos;

    @Column
    private String email;

    public UsuarioComprador() {
    }

    public UsuarioComprador(String nombre, String apellidos, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UsuarioComprador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public String informacionUsuario() {
        return getNombreCompleto().concat(" ").concat(getEmail());
    }

    @Override
    public String getNombreCompleto() {
        return getNombre().concat(" ").concat(getApellidos());
    }
}
