package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.dao.LiniaFacturaDAO;
import com.gruptd.medicPet.dao.FacturaDAO;
import com.gruptd.medicPet.models.LiniaFactura;
import com.gruptd.medicPet.models.Factura;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class BillingController {
    @Autowired
    private FacturaDAO facturaDao;
    
    @Autowired
    private LiniaFacturaDAO liniaFacturaDao;
    
    @GetMapping("/factures")
    public String principalTreballadors() {
        log.info("Executant el controlador de factures");
        Iterable<Factura> factures = facturaDao.findAll();
        log.info(">>> Factures de la BBDD:");
        factures.forEach((t) -> {
            log.info(t.getMetodePagament());
        });
        
        Iterable<LiniaFactura> linies = liniaFacturaDao.findAll();
        log.info(">>> Linia de factures de la BBDD:");
        linies.forEach((t) -> {
            log.info(t.getFactura().getMetodePagament());
        });
        return "login";
    }
}
