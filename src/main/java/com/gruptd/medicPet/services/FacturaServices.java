package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.FacturaDAO;
import com.gruptd.medicPet.models.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pmorante
 */
@Service
public class FacturaServices implements ServicesInterface<Factura> {
    @Autowired
    private FacturaDAO facturaDao;
    
    @Transactional(readOnly = true)
    @Override
    public Iterable<Factura> findAll() {
        return facturaDao.findAll();
    }
    
    @Transactional
    @Override
    public void save(Factura f) {
        facturaDao.save(f);
    }
    
    @Transactional
    @Override
    public void delete(Factura f) {
        facturaDao.delete(f);
    }
    
    @Transactional(readOnly = true)
    @Override
    public Factura getOne(Factura factura) {
        return facturaDao.findById(factura.getId()).orElse(null);
    }
    
    @Transactional
    @Override
    public void update(Factura f) {
        Factura facturaBD = getOne(f);
        if (facturaBD != null) {
            facturaBD.setDataPagament(f.getDataPagament());
            facturaBD.setMetodePagament(f.getMetodePagament());
            
            save(facturaBD);
        } else {
            System.out.println("La factura no existeix.");
        }
    }
}
