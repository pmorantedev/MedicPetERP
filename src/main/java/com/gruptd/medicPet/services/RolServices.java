package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.RolDAO;
import com.gruptd.medicPet.models.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pmorante
 */
@Service
public class RolServices implements ServicesInterface<Rol> {
    
    @Autowired
    private RolDAO rolDao;

    @Transactional(readOnly = true)
    @Override
    public Iterable findAll() {
        return rolDao.findAll();
    }

    @Transactional
    @Override
    public void save(Rol r) {
        rolDao.save(r);
    }

    @Transactional
    @Override
    public void delete(Rol r) {
        rolDao.delete(r);
    }

    @Override
    @Transactional
    public Rol getOne(Long id) {
        return rolDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public void update(Rol r) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
