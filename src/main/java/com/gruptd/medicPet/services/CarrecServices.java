package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.CarrecDAO;
import com.gruptd.medicPet.models.Carrec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Aquesta clase crea totes funcions necessàries del CRUD parcial amb la BBDD
 * sobre la clase Carrec.
 *
 * @author pmorante
 */
@Service
public class CarrecServices implements ServicesInterface<Carrec> {

    @Autowired
    private CarrecDAO carrecDao;

    @Transactional(readOnly = true)
    public Carrec getCarrec(Carrec c) {
        return carrecDao.findById(c.getId()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Carrec> findAll() {
        return carrecDao.findAll();
    }

    @Override
    public void save(Carrec item) {
        throw new UnsupportedOperationException("Encara no admès.");
    }

    @Override
    public void delete(Carrec item) {
        throw new UnsupportedOperationException("Encara no admès.");
    }

    @Override
    @Transactional(readOnly = true)
    public Carrec getOne(Long id) {
        return carrecDao.findById(id).orElse(null);
    }

    @Override
    public void update(Carrec item) {
        throw new UnsupportedOperationException("Encara no admès.");
    }

}
