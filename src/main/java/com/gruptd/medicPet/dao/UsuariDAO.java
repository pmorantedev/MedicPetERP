package com.gruptd.medicPet.dao;

import com.gruptd.medicPet.models.Usuari;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author izan
 */
public interface UsuariDAO extends CrudRepository<Usuari, Long> {
    Optional<Usuari> findByUsername(String username);
}
