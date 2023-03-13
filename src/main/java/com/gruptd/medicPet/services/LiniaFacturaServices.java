package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.LiniaFacturaDAO;
import com.gruptd.medicPet.models.LiniaFactura;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pmorante
 */
@Service
public class LiniaFacturaServices {
    
    @Autowired
    private LiniaFacturaDAO liniaFacturaDao;
    
    @Transactional(readOnly = true)
    public List<LiniaFactura> findAllLiniesFactures() {
        return (List<LiniaFactura>) liniaFacturaDao.findAll();
    }
    
    @Transactional
    public void saveLiniaFactura(LiniaFactura lf) {
        liniaFacturaDao.save(lf);
    }
    
    @Transactional
    public void deleteLiniaFactura(LiniaFactura lf) {
        liniaFacturaDao.delete(lf);
    }
    
    @Transactional(readOnly = true)
    public LiniaFactura getLiniaFactura(LiniaFactura liniaFactura) {
        
        return liniaFacturaDao.findByNumeroDeLineaAndFactura(liniaFactura.getNumeroDeLinea(), liniaFactura.getFactura());
    }
    
    @Transactional
    public void updateTreballador(LiniaFactura lf) {
        LiniaFactura liniaFacturaBD = getLiniaFactura(lf);
        if (liniaFacturaBD != null) {
            liniaFacturaBD.setQuantitat(lf.getQuantitat());
            liniaFacturaBD.setTotal(lf.getTotal());
            liniaFacturaBD.setTractament(lf.getTractament());
            
            saveLiniaFactura(liniaFacturaBD);
        } else {
            System.out.println("La línia de factura no existeix.");
        }
    }
    
}
