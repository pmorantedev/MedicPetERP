/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gruptd.medicPet.dao;

import com.gruptd.medicPet.models.Mascota;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author izan
 */
public interface MascotaDAO extends CrudRepository<Mascota, Long>  {
    
}
