package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.TractamentDAO;
import com.gruptd.medicPet.models.Tractament;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pmorante
 */
@Service
public class TractamentServices {
    
    @Autowired
    private TractamentDAO tractamentDao;
    
    @Transactional(readOnly = true)
    public List<Tractament> findAllTractaments() {
        return (List<Tractament>) tractamentDao.findAll();
    }
    
    @Transactional
    public void saveTractament(Tractament t) {
        tractamentDao.save(t);
    }
    
    @Transactional
    public void deleteTractament(Tractament t) {
        tractamentDao.delete(t);
    }
    
    @Transactional(readOnly = true)
    public Tractament getTractament(Tractament tractament) {
        return tractamentDao.findById(tractament.getId()).orElse(null);
    }
    
    @Transactional
    public void updateTreballador(Tractament t) {
        Tractament tractamentBD = getTractament(t);
        if (tractamentBD != null) {
            tractamentBD.setNom(t.getNom());
            tractamentBD.setPreu(t.getPreu());
            
            saveTractament(tractamentBD);
        } else {
            System.out.println("El tractament no existeix.");
        }
    }
    
}
