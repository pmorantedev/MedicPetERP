/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gruptd.medicPet.dao;

import com.gruptd.medicPet.models.Mascota;
import com.gruptd.medicPet.models.Visita;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author izan
 */
public interface VisitaDAO extends CrudRepository<Visita, Long> {

    Iterable<Visita> findByMascota (Mascota mascota);
}
