package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.FacturaDAO;
import com.gruptd.medicPet.models.Factura;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pmorante
 */
@Service
public class FacturaServices {
    @Autowired
    private FacturaDAO facturaDao;
    
    @Transactional(readOnly = true)
    public List<Factura> findAllFactures() {
        return (List<Factura>) facturaDao.findAll();
    }
    
    @Transactional
    public void saveFactura(Factura f) {
        facturaDao.save(f);
    }
    
    @Transactional
    public void deleteFactura(Factura f) {
        facturaDao.delete(f);
    }
    
    @Transactional(readOnly = true)
    public Factura getFactura(Factura factura) {
        return facturaDao.findById(factura.getId()).orElse(null);
    }
    
    @Transactional
    public void updateTreballador(Factura f) {
        Factura facturaBD = getFactura(f);
        if (facturaBD != null) {
            facturaBD.setDataPagament(f.getDataPagament());
            facturaBD.setMetodePagament(f.getMetodePagament());
            
            saveFactura(facturaBD);
        } else {
            System.out.println("La factura no existeix.");
        }
    }
}
