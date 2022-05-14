package com.jmunoz.springbootbooksjpa.app.repository;

import com.jmunoz.springbootbooksjpa.app.models.LibroWeb;
import org.springframework.data.repository.CrudRepository;

public interface LibroWebDao extends CrudRepository<LibroWeb, Integer> {

    Iterable<LibroWeb> findLibroByUsuarioId(int id);

    void deleteByUsuarioId(int id);
}
