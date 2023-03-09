/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gruptd.medicPet.dao;

import com.gruptd.medicPet.models.Client;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author izan
 */
public interface ClientDAO extends CrudRepository<Client, Long> {

}
