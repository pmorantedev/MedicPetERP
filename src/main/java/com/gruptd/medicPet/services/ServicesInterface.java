package com.gruptd.medicPet.services;

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
