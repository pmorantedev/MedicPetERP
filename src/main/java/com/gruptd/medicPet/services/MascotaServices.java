package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.MascotaDAO;
import com.gruptd.medicPet.models.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Aquesta clase crea totes les funcions del CRUD amb la BBDD sobre la clase
 * Mascota. S'ha d'importar i es pot utilitzar per fer peticions i modificacions
 * a la BBDD a la taula de Mascota
 *
 * @author izan
 */
@Service
public class MascotaServices implements ServicesInterface<Mascota> {

    @Autowired
    private MascotaDAO mascotaDao;

    @Transactional(readOnly = true)
    @Override
    public Iterable<Mascota> findAll() {
        return mascotaDao.findAll();
    }

    @Transactional
    @Override
    public void save(Mascota m) {
        mascotaDao.save(m);
    }

    @Transactional
    @Override
    public void delete(Mascota m) {
        mascotaDao.delete(m);
    }

    @Transactional(readOnly = true)
    @Override
    public Mascota getOne(Long id) {
        return mascotaDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void update(Mascota m) {
        Mascota mascotaDB = getOne(m.getId_mascota());
        if (mascotaDB != null) {
            mascotaDB.setNom(m.getNom());

            save(mascotaDB);
        } else {
            System.out.println("La mascota no existeix.");
        }
    }

}
