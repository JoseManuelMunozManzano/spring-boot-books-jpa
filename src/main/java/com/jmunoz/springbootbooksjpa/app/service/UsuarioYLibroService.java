package com.jmunoz.springbootbooksjpa.app.service;

import com.jmunoz.springbootbooksjpa.app.models.LibroFisico;
import com.jmunoz.springbootbooksjpa.app.models.LibroWeb;
import com.jmunoz.springbootbooksjpa.app.models.UsuarioComprador;
import com.jmunoz.springbootbooksjpa.app.repository.LibroFisicoDao;
import com.jmunoz.springbootbooksjpa.app.repository.LibroWebDao;
import com.jmunoz.springbootbooksjpa.app.repository.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioYLibroService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    @Qualifier("libroFisico")
    private LibroFisico libroFisico;

    @Autowired
    private LibroFisicoDao libroFisicoDao;

    @Autowired
    @Qualifier("libroWeb")
    private LibroWeb libroWeb;

    @Autowired
    private LibroWebDao libroWebDao;

    public void createUsuario(String nombre, String apellidos, String email) {
        UsuarioComprador usuario = new UsuarioComprador(nombre, apellidos, email);
        usuario.setId(0);
        usuarioDao.save(usuario);
    }

    public boolean checkIfUsuarioIsNull(Integer id) {
        Optional<UsuarioComprador> usuario = usuarioDao.findById(id);

        return usuario.isEmpty();
    }

    public void deleteUsuario(Integer id) {
        if (!checkIfUsuarioIsNull(id)) {
            usuarioDao.deleteById(id);
        }
    }

    public Iterable<UsuarioComprador> getBiblioteca() {
        return usuarioDao.findAll();
    }

    public boolean createLibro(String libro, int usuarioId, String tipoLibro) {
        if (checkIfUsuarioIsNull(usuarioId)) {
            return false;
        }

        if (tipoLibro.equals("Físico")) {
            libroFisico.setId(0);
            libroFisico.setLibro(libro);
            libroFisico.setUsuarioId(usuarioId);
            libroFisicoDao.save(libroFisico);

            return true;
        }

        if (tipoLibro.equals("Web")) {
            libroWeb.setId(0);
            libroWeb.setLibro(libro);
            libroWeb.setUsuarioId(usuarioId);
            libroWebDao.save(libroWeb);

            return true;
        }

        return false;
    }

}
