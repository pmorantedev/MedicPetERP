package com.gruptd.medicPet.dao;

import com.gruptd.medicPet.models.Treballador;
import com.gruptd.medicPet.models.Usuari;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author pmorante
 */
public interface TreballadorDAO extends CrudRepository<Treballador, Long> {
    
    Optional<Treballador> findByUsuari(Usuari user);
    
}
