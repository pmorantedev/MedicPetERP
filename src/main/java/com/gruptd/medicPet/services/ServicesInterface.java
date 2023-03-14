/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gruptd.medicPet.services;

import java.util.List;

/**
 *
 * @author pmorante
 */
public interface ServicesInterface<T> {
    
    public Iterable<T> findAll();
    
    public void save(T item);
    
    public void delete(T item);
    
    public T getOne(T item);
    
    public void update(T item);
    
}
