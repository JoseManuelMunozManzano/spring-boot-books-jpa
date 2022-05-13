package com.jmunoz.springbootbooksjpa.app.repository;

import com.jmunoz.springbootbooksjpa.app.models.LibroKindle;
import org.springframework.data.repository.CrudRepository;

public interface LibroKindleDao extends CrudRepository<LibroKindle, Integer> {

    Iterable<LibroKindle> findLibroByUsuarioId(int id);
}
