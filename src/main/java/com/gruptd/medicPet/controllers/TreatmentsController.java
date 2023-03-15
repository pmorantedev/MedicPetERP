package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.services.TractamentServices;
import com.gruptd.medicPet.models.Tractament;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class TreatmentsController {

    @Autowired
    private TractamentServices tractamentService;

    @GetMapping("/medicpet/tractaments")
    public String principalTractament(Model model) {
        log.info("Executant el controlador de tractaments");
        Iterable<Tractament> tractaments = tractamentService.findAll();
        log.info(">>> Tractaments de la BBDD:");
        tractaments.forEach((t) -> {
            log.info(t.getNom());
        });
        
        model.addAttribute("tractaments", tractaments);
        
        return "tractamentsMain";
    }
    
    @PostMapping("/medicpet/tractaments-fitxa/{id}")
    public String desarTractament(Tractament tractament) {
        tractamentService.save(tractament);
        
        return "redirect:/tractaments";
    }
    
    @GetMapping("/medicpet/tractaments-fitxa")
    public String fitxaTractament(Tractament tractament) {
        
        return "tractamentsForm";
    }
    
    @PostMapping("/medicpet/eliminar/{id}")
    public String eliminar(Tractament tractament){
        tractamentService.delete(tractament);
        return "redirect:/tractaments";
    }
    
    @PostMapping("/medicpet/guardar")
    public String guardar(Tractament tractament){
        tractamentService.save(tractament);
        return "redirect:/tractaments";
    }
    
}
