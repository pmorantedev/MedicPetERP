/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.VisitaDAO;
import com.gruptd.medicPet.models.Mascota;
import com.gruptd.medicPet.models.Visita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Aquesta clase crea totes les funcions del CRUD amb la BBDD sobre la clase
 * Vista. S'ha d'importar i es pot utilitzar per fer peticions i modificacions a
 * la BBDD a la taula de Visita
 *
 *
 * @author izan
 */
@Service
public class VisitaServices implements ServicesInterface<Visita> {

    @Autowired
    private VisitaDAO visitaDao;

    @Transactional(readOnly = true)
    @Override
    public Iterable<Visita> findAll() {
        return visitaDao.findAll();
    }

    @Transactional
    @Override
    public void save(Visita v) {
        visitaDao.save(v);
    }

    @Transactional
    @Override
    public void delete(Visita v) {
        visitaDao.delete(v);
    }

    @Transactional(readOnly = true)
    public Visita getOne(Long id) {
        return visitaDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void update(Visita v) {
        Visita visitaDB = getOne(v.getId());
        if (visitaDB != null) {
            visitaDB.setDiagnostic(v.getDiagnostic());
            visitaDB.setMedicacio(v.getMedicacio());
            visitaDB.setData_visita(v.getData_visita());

            visitaDB.setTreballador_id(v.getTreballador_id());
            visitaDB.setMascota(v.getMascota());

            save(visitaDB);
        } else {
            System.out.println("La visita no existeix.");
        }
    }
    
    @Transactional(readOnly = true)
    public Iterable<Visita> findAllByMascota (Mascota mascota){
        return visitaDao.findByMascota(mascota);
    }

}
