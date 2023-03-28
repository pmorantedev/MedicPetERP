package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.services.FacturaServices;
import com.gruptd.medicPet.services.LiniaFacturaServices;
import com.gruptd.medicPet.models.LiniaFactura;
import com.gruptd.medicPet.models.Factura;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class FacturaController {

    @Autowired
    private FacturaServices facturaService;

    @Autowired
    private LiniaFacturaServices liniaFacturaService;

    @GetMapping("/medicpet/factures")
    public String principalFactures(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userName", username);
        model.addAttribute("pagina", "Factures");
        log.info("Executant el controlador de factures");
        Iterable<Factura> factures = facturaService.findAll();
        log.info(">>> Factures de la BBDD:");
        factures.forEach((t) -> {
            log.info(t.getMetodePagament());
        });

        Iterable<LiniaFactura> linies = liniaFacturaService.findAll();
        log.info(">>> Linia de factures de la BBDD:");
        linies.forEach((t) -> {
            log.info(t.getFactura().getMetodePagament());
        });
        return "facturacioMain";
    }

    @GetMapping("/medicpet/detall-factura")
    public String detallFactura() {
        log.info("Executant el controlador de facturació: Detall de factura");
        return "facturacioDetall";
    }
}
