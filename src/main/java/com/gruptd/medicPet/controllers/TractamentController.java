package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.services.TractamentServices;
import com.gruptd.medicPet.models.Tractament;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class TractamentController {

    @Autowired
    private TractamentServices tractamentService;

    @GetMapping("/medicpet/tractaments")
    public String principalTractament(Model model) {
        Iterable<Tractament> tractaments = tractamentService.findAll();
        model.addAttribute("tractaments", tractaments);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userName", username);
        
        return "tractamentsMain";
    }
    
    @GetMapping("/medicpet/tractaments/fitxa/{id}")
    public String desarTractament(Tractament tractament, Model model) {
        tractament = tractamentService.getOne(tractament.getId());
        model.addAttribute("tractament", tractament);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userName", username);
        
        return "tractamentsForm";
    }
    
    @GetMapping("/medicpet/tractaments/fitxa")
    public String fitxaTractament(Tractament tractament, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userName", username);
        
        return "tractamentsForm";
    }
    
    @PostMapping("/medicpet/tractaments/eliminar/{id}")
    public String eliminar(Tractament tractament){
        tractamentService.delete(tractament);
        return "redirect:/medicpet/tractaments";
    }
    
    @PostMapping("/medicpet/tractaments/guardar")
    public String guardar(Tractament tractament){
        tractamentService.save(tractament);
        return "redirect:/medicpet/tractaments";
    }
    
}
