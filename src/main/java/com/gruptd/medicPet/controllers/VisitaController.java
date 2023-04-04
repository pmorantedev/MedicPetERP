package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.Carrec;
import com.gruptd.medicPet.models.Mascota;
import com.gruptd.medicPet.models.Treballador;
import com.gruptd.medicPet.models.Visita;
import com.gruptd.medicPet.services.CarrecServices;
import com.gruptd.medicPet.services.MascotaServices;
import com.gruptd.medicPet.services.TreballadorServices;
import com.gruptd.medicPet.services.VisitaServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.Errors;

@Controller
@Slf4j
public class VisitaController {
    
    @Autowired
    private VisitaServices visitaServices;
    @Autowired
    private TreballadorServices treballadorServices;
    @Autowired
    private CarrecServices carrecServices;
    @Autowired
    private MascotaServices mascotaServices;
    
    @GetMapping("/medicpet/visites/{id_mascota}/fitxa")
    public String principalVisita(Mascota mascota, Visita visita, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Carrec carrec = carrecServices.getOne(1L);
        Iterable<Treballador> veterinaris = treballadorServices.getByCarrec(carrec);
        Mascota mascota2 = mascotaServices.getOne(mascota.getId_mascota());
        
        model.addAttribute("mascota", mascota2);
        model.addAttribute("userName", username);
        model.addAttribute("pagina", "Clients");
        model.addAttribute("veterinaris", veterinaris);
        
        return "visitaForm";
    }
    
    @PostMapping("/medicpet/visites/fitxa/desar")          
    public String desarVisita(Visita visita, Model model, Errors errors) {

        if (errors.hasErrors()) { // Si s'han produït errors...
            return "visitaForm"; // Mantenim l'usuari a la pàgina del formulari
        }
        System.out.println("VISITAAAAAAAAA " + visita);
        if (visita.getId() == null) {
            visitaServices.save(visita);
        } else {
            visitaServices.update(visita);
        }

        return "redirect:/medicpet/clients";
    }
    
    @GetMapping("/medicpet/visites/{id_mascota}/fitxa/{id}/editar") 
    public String modificarVisita(Mascota mascota, Visita visita, Model model) {

        visita = visitaServices.getOne(visita.getId());
        model.addAttribute("visita", visita);
        Carrec carrec = carrecServices.getOne(1L);
        Iterable<Treballador> veterinaris = treballadorServices.getByCarrec(carrec);
        Mascota mascota2 = mascotaServices.getOne(mascota.getId_mascota());
        
        model.addAttribute("mascota", mascota2);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userName", username);
        model.addAttribute("pagina", "Clients");
        model.addAttribute("veterinaris", veterinaris);

        return "visitaForm";
    }
    
    @PostMapping("/medicpet/visites/fitxa/eliminar/{id}")
    public String eliminar(Visita visita, Model model) {            
        
        // Eliminem visita
        visitaServices.delete(visita);

        return "redirect:/medicpet/clients/fitxa/{client_id}";
    }
}
