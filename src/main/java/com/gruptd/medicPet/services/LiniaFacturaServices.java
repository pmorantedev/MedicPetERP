package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.LiniaFacturaDAO;
import com.gruptd.medicPet.models.LiniaFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pmorante
 */
@Service
public class LiniaFacturaServices implements ServicesInterface<LiniaFactura> {
    
    @Autowired
    private LiniaFacturaDAO liniaFacturaDao;
    
    @Transactional(readOnly = true)
    @Override
    public Iterable<LiniaFactura> findAll() {
        return liniaFacturaDao.findAll();
    }
    
    @Transactional
    @Override
    public void save(LiniaFactura lf) {
        liniaFacturaDao.save(lf);
    }
    
    @Transactional
    @Override
    public void delete(LiniaFactura lf) {
        liniaFacturaDao.delete(lf);
    }
    
    @Transactional(readOnly = true)
    @Override
    public LiniaFactura getOne(LiniaFactura liniaFactura) {
        
        return liniaFacturaDao.findByNumeroDeLineaAndFactura(liniaFactura.getNumeroDeLinea(), liniaFactura.getFactura());
    }
    
    @Transactional
    @Override
    public void update(LiniaFactura lf) {
        LiniaFactura liniaFacturaBD = getOne(lf);
        if (liniaFacturaBD != null) {
            liniaFacturaBD.setQuantitat(lf.getQuantitat());
            liniaFacturaBD.setTotal(lf.getTotal());
            liniaFacturaBD.setTractament(lf.getTractament());
            
            save(liniaFacturaBD);
        } else {
            System.out.println("La línia de factura no existeix.");
        }
    }
    
}
