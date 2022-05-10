package com.jmunoz.springbootbooksjpa.app.service;

import com.jmunoz.springbootbooksjpa.app.models.UsuarioComprador;
import com.jmunoz.springbootbooksjpa.app.repository.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioYLibroService {

    @Autowired
    private UsuarioDao usuarioDao;

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
}
