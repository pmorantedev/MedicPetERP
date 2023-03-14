/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.VisitaDAO;
import com.gruptd.medicPet.models.Visita;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author izan
 */
public class VisitaServices {

    @Autowired
    private VisitaDAO visitaDao;

    @Transactional(readOnly = true)
    public List<Visita> findAllVisites() {
        return (List<Visita>) visitaDao.findAll();
    }

    @Transactional
    public void saveVisita(Visita v) {
        visitaDao.save(v);
    }

    @Transactional
    public void deleteVisita(Visita v) {
        visitaDao.delete(v);
    }

    @Transactional(readOnly = true)
    public Visita getVisita(Visita visita) {
        return visitaDao.findById(visita.getId()).orElse(null);
    }

    @Transactional
    public void updateVisita(Visita v) {
        Visita visitaDB = getVisita(v);
        if (visitaDB != null) {
            visitaDB.setData_visita(v.getData_visita());

            saveVisita(visitaDB);
        } else {
            System.out.println("La visita no existeix.");
        }
    }

}
