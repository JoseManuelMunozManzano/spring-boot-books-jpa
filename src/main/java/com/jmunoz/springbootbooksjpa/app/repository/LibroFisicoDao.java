package com.jmunoz.springbootbooksjpa.app.repository;

import com.jmunoz.springbootbooksjpa.app.models.LibroFisico;
import org.springframework.data.repository.CrudRepository;

public interface LibroFisicoDao extends CrudRepository<LibroFisico, Integer> {

    Iterable<LibroFisico> findLibroByUsuarioId(int id);

}
