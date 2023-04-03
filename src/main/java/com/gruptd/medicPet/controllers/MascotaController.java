package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.Client;
import com.gruptd.medicPet.models.Mascota;
import com.gruptd.medicPet.models.Visita;
import com.gruptd.medicPet.services.MascotaServices;
import com.gruptd.medicPet.services.VisitaServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class MascotaController {

    @Autowired
    private MascotaServices mascotaService;
    @Autowired
    private VisitaServices visitaService;
    
    @GetMapping("/medicpet/clients/fitxa/{client_id}/mascotes/fitxa")           // URL fitxa mascota (FORM)
    public String fitxaMascota(Mascota mascota, Model model) {
        log.info("Executant el controlador de mascotes: FORMULARI OBERT...");
        model.addAttribute("pagina", "Clients");

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userName", username);

        return "mascotaForm";
    }

    @PostMapping("/medicpet/clients/fitxa/{client_id}/mascotes/desar")          // URL 'CREATE' mascota (FORM)***
    public String desarMascota(Mascota mascota, Model model, Errors errors) {
        log.info("Executant el controlador de mascotes: DESANT DADES MASCOTA...");
        
        if (errors.hasErrors()) { // Si s'han produït errors...
            return "mascotaForm"; // Mantenim l'usuari a la pàgina del formulari
        }

        mascotaService.save(mascota);

        return "redirect:/medicpet/clients/fitxa/{client_id}";
    }

    @GetMapping("/medicpet/clients/fitxa/{client_id}/mascotes/fitxa/{id_mascota}/editar") // URL 'UPDATE' mascota (FORM)
    public String modificarMascota(Mascota mascota, Model model) {
        log.info("Executant el controlador de mascotes: EDITANT...");

        mascota = mascotaService.getOne(mascota.getId_mascota());
        model.addAttribute("mascota", mascota);
        model.addAttribute("pagina", "Clients");
        
        Iterable<Visita> visites = visitaService.findAll();
        model.addAttribute("visites", visites);
        log.info(">> Visites: ", visites.toString());
        
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userName", username);

        return "mascotaForm";
    }

    @PostMapping("/medicpet/clients/fitxa/{client_id}/mascotes/eliminar/{id_mascota}") // URL 'DELETE' mascota (FORM)
    public String eliminar(Mascota mascota, RedirectAttributes redirectAtr) {        
        
        // Recupero mascota per mostrar el nom per consola i passar-lo a la vista
        mascota = mascotaService.getOne(mascota.getId_mascota());
        log.info("Executant controlador mascotes: MASCOTA ELIMINADA( ID:" + mascota.getId_mascota() + ", " + mascota.getNom() + ")...");
        redirectAtr.addAttribute("nomRegistreEliminat", mascota.getNom());        
        
        // Executo l'acció d'eliminar
        mascotaService.delete(mascota);
        redirectAtr.addAttribute("registreEliminat", true);

        return "redirect:/medicpet/clients/fitxa/{client_id}";
    }

}
