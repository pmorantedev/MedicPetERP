package com.gruptd.medicPet.dao;

import com.gruptd.medicPet.models.Carrec;
import com.gruptd.medicPet.models.Treballador;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author pmorante
 */
public interface TreballadorDAO extends CrudRepository<Treballador, Long> {
    
    Iterable<Treballador> findByCarrec(Carrec carrec);
    
}
