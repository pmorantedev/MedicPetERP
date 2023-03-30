package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.TractamentDAO;
import com.gruptd.medicPet.models.Tractament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Aquesta clase crea totes les funcions del CRUD amb la BBDD sobre la clase
 * Tractament. S'ha d'importar i es pot utilitzar per fer peticions i
 * modificacions a la BBDD a la taula de Tractament
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
    public Tractament getOne(Long id) {
        return tractamentDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void update(Tractament t) {
        Tractament tractamentBD = getOne(t.getId());
        if (tractamentBD != null) {
            tractamentBD.setNom(t.getNom());
            tractamentBD.setPreu(t.getPreu());

            save(tractamentBD);
        } else {
            System.out.println("El tractament no existeix.");
        }
    }

}
