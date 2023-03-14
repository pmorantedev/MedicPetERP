package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.services.TractamentServices;
import com.gruptd.medicPet.models.Tractament;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TreatmentsController {
    
    @Autowired
    private TractamentServices tractamentService;
    
    @GetMapping("/tractaments")
    public String principalTractament() {
        log.info("Executant el controlador de tractaments");
        Tractament tractament = new Tractament();
        tractament.setId(2L);
        tractament.setNom("update");
        tractament.setPreu(12.33f);
        tractamentService.updateTreballador(tractament);
//        tractamentService.saveTractament(tractament);
//        Tractament tractament2 = new Tractament();
//        tractament2.setId(3L);
//        tractament2.setNom("Prueba4");
//        tractament2.setPreu(6.73f);
//        tractamentService.saveTractament(tractament2);
//        tractamentService.deleteTractament(tractament2);
        Iterable<Tractament> tractaments = tractamentService.findAllTractaments();
        log.info(">>> Tractaments de la BBDD:");
        tractaments.forEach((t) -> {
            log.info(t.getNom());
        });
        return "tractamentsMain";
    }
    
}
