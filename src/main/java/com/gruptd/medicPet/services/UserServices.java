/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.UsuariDAO;
import com.gruptd.medicPet.models.Usuari;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author izan
 */
@Service
public class UserServices {

    @Autowired
    private UsuariDAO usuariDao;

    @Transactional(readOnly = true)
    public List<Usuari> findAllUsuaris() {
        return (List<Usuari>) usuariDao.findAll();
    }

    @Transactional
    public void saveUsuari(Usuari u) {
        usuariDao.save(u);
    }

    @Transactional
    public void deleteUsuari(Usuari u) {
        usuariDao.delete(u);
    }

    @Transactional(readOnly = true)
    public Usuari getUsuari(Usuari client) {
        try {
            return usuariDao.findById(Long.valueOf(client.getId())).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public void updateUsuari(Usuari u) {
        Usuari usuariBD = getUsuari(u);
        if (usuariBD != null) {
            usuariBD.setNom(u.getNom());

            saveUsuari(usuariBD);
        } else {
            System.out.println("L'usuari no existeix.");
        }
    }
}
