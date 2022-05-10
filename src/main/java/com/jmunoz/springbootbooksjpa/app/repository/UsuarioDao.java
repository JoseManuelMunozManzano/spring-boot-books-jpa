package com.jmunoz.springbootbooksjpa.app.repository;

import com.jmunoz.springbootbooksjpa.app.models.UsuarioComprador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDao extends CrudRepository<UsuarioComprador, Integer> {

    UsuarioComprador findByEmail(String email);
}
