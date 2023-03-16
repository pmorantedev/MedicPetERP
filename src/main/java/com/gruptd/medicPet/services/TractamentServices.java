package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.TractamentDAO;
import com.gruptd.medicPet.models.Tractament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pmorante
 */
@Service
public class TractamentServices implements ServicesInterface<Tractament> {
    
    @Autowired
    private TractamentDAO tractamentDao;
    
    
    @Transactional(readOnly = true)
    @Override
    public Iterable<Tractament> findAll() {
        return tractamentDao.findAll();
    }
    
    @Transactional
    @Override
    public void save(Tractament t) {
        tractamentDao.save(t);
    }
    
    @Transactional
    @Override
    public void delete(Tractament t) {
        tractamentDao.delete(t);
    }
    
    @Transactional(readOnly = true)
    @Override
    public Tractament getOne(Tractament tractament) {
        return tractamentDao.findById(tractament.getId()).orElse(null);
    }
    
    @Transactional
    @Override
    public void update(Tractament t) {
        Tractament tractamentBD = getOne(t);
        if (tractamentBD != null) {
            tractamentBD.setNom(t.getNom());
            tractamentBD.setPreu(t.getPreu());
            
            save(tractamentBD);
        } else {
            System.out.println("El tractament no existeix.");
        }
    }
    
}
