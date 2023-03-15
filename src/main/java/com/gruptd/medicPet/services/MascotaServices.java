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
 * @author izan
 */
public class MascotaServices {
    @Autowired
    private MascotaDAO mascotaDao;

    @Transactional(readOnly = true)
    public List<Mascota> findAllMascotes() {
        return (List<Mascota>) mascotaDao.findAll();
    }

    @Transactional
    public void saveMascota(Mascota m) {
        mascotaDao.save(m);
    }

    @Transactional
    public void deleteMascota(Mascota m) {
        mascotaDao.delete(m);
    }

    @Transactional(readOnly = true)
    public Mascota getMascota(Mascota mascota) {
        return mascotaDao.findById(mascota.getId_mascota()).orElse(null);
    }

    @Transactional
    public void updateMascota(Mascota m) {
        Mascota mascotaDB = getMascota(m);
        if (mascotaDB != null) {
            mascotaDB.setNom(m.getNom());

            saveMascota(mascotaDB);
        } else {
            System.out.println("La mascota no existeix.");
        }
    }

    
    
}
