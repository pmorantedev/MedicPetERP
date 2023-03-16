/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.MascotaDAO;
import com.gruptd.medicPet.models.Mascota;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Aquesta clase crea totes les funcions del CRUD amb la BBDD sobre la clase
 * Mascota. S'ha d'importar i es pot utilitzar per fer peticions i modificacions
 * a la BBDD a la taula de Mascota
 *
 * @author izan
 */
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
    public Mascota getOne(Mascota mascota) {
        return mascotaDao.findById(mascota.getId_mascota()).orElse(null);
    }

    @Transactional
    @Override
    public void update(Mascota m) {
        Mascota mascotaDB = getOne(m);
        if (mascotaDB != null) {
            mascotaDB.setNom(m.getNom());

            save(mascotaDB);
        } else {
            System.out.println("La mascota no existeix.");
        }
    }

}
