package com.gruptd.medicPet.dao;

import com.gruptd.medicPet.models.Mascota;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author izan
 */
public interface MascotaDAO extends CrudRepository<Mascota, Long>  {
    
}
