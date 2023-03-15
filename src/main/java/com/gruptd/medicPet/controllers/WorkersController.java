package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.Carrec;
import com.gruptd.medicPet.models.Treballador;
import com.gruptd.medicPet.services.CarrecServices;
import com.gruptd.medicPet.services.TreballadorServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class WorkersController {
    @Autowired
    private TreballadorServices treballadorService;
    
    @Autowired
    private CarrecServices carrecService;
    
    @GetMapping("/medicpet/rrhh")
    public String principalTreballadors() {
        log.info("Executant el controlador de treballador");
        Iterable<Treballador> treballadors = treballadorService.findAll();
        log.info(">>> Treballadors de la BBDD:");
        treballadors.forEach((t) -> {
            log.info(t.getNomComplet());
        });
        
        Iterable<Carrec> carrecs = carrecService.findAllCarrecs();
        log.info(">>> Carrcs de la BBDD:");
        carrecs.forEach((t) -> {
            log.info(t.getNom());
        });
        return "rrhhMain";
    }
}
