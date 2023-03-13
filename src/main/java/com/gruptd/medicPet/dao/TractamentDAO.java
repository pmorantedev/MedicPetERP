package com.gruptd.medicPet.dao;

import com.gruptd.medicPet.models.Tractament;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author pmorante
 */
public interface TractamentDAO extends CrudRepository<Tractament, Long> {
    
}
