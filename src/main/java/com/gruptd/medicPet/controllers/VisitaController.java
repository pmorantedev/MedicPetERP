package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.Usuari;
import com.gruptd.medicPet.models.Visita;
import com.gruptd.medicPet.services.UsuariServices;
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
    private UsuariServices usuariService;
    
    @Autowired
    private VisitaServices visitaServices;
    
    @GetMapping("/medicpet/visites")
    public String principalTractament(Model model) {
        
        Iterable<Visita> visites = visitaServices.findAll();
        model.addAttribute("visites", visites);
        
        // Recuperar el nom de l'usuari actual
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Recuperar l'objecte Usuari corresponent a l'usuari actual
        Usuari usuari = usuariService.getByUsername(username);
        // Accedir a l'atribut 'Nom' per mostrar-lo al header
        String nomUsuariComplert = usuari.getNom();
        model.addAttribute("userName", username);
        model.addAttribute("nomUsuariComplert", nomUsuariComplert);
        
        model.addAttribute("pagina", "Clients");
        
        return "tractamentsMain";
    }
    
    @GetMapping("/medicpet/clients/fitxa/{client_id}/mascotes/fitxa/{id_mascota}/visites/{id_visita}") // URL 'UPDATE' mascota (FORM)
    public String principalVisites(Visita visita, Model model) {
//        log.info("Executant el controlador de visites: EDITANT...");
//
//        mascota = mascotaService.getOne(mascota.getId_mascota());
//        model.addAttribute("mascota", mascota);
//        model.addAttribute("pagina", "Clients");
//        
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        model.addAttribute("userName", username);
//        
        return "mascotaForm";
    }
}
