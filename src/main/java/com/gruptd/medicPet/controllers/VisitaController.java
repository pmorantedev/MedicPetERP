package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.Visita;
import com.gruptd.medicPet.services.VisitaServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class VisitaController {
    
    @Autowired
    private VisitaServices visitaServices;
    
    @GetMapping("/medicpet/visites")
    public String principalTractament(Model model) {
        Iterable<Visita> visites = visitaServices.findAll();
        model.addAttribute("visites", visites);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userName", username);
        model.addAttribute("pagina", "Clients");
        
        return "tractamentsMain";
    }
}
