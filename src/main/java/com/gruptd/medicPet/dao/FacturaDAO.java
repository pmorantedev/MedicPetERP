package com.gruptd.medicPet.dao;

import com.gruptd.medicPet.models.Factura;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author pmorante
 */
public interface FacturaDAO extends CrudRepository<Factura, Long> {
    
}
