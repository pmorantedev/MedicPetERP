package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.dao.TractamentDAO;
import com.gruptd.medicPet.models.Tractament;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TreatmentsController {
    
    @Autowired
    private TractamentDAO tractamentDao;
    
    @GetMapping("/tractments")
    public String principalTractament() {
        log.info("Executant el controlador de tractaments");
        Iterable<Tractament> tractaments = tractamentDao.findAll();
        log.info(">>> Tractaments de la BBDD:");
        tractaments.forEach((t) -> {
            log.info(t.getNom());
        });
        return "login";
    }
    
}
