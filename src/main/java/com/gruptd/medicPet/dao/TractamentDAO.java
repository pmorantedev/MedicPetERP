package com.gruptd.medicPet.dao;

import com.gruptd.medicPet.models.Tractament;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author pmorante 
 */
public interface TractamentDAO extends CrudRepository<Tractament, Long> {

//    @Query("SELECT t FROM tractament t WHERE t.nom LIKE %?1%")
//    public List<Tractament> findAll(String paraulaClau);
}
