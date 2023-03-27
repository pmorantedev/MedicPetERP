package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.Client;
import com.gruptd.medicPet.models.Mascota;
import com.gruptd.medicPet.services.MascotaServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MascotaController {

    @Autowired
    private MascotaServices mascotaService;


//    @GetMapping("/medicpet/clients/fitxa/{client_id}")
//    public String principalMascotes(Model model) {
//        log.info("Executant el controlador de clients");
//        Iterable<Mascota> mascotes = mascotaService.findAll();
//        
//        model.addAttribute("mascotes", mascotes);
//        
//        return "mascotesMain";
//    }
    @GetMapping("/medicpet/clients/fitxa/{client_id}/mascotes/fitxa")            // URL fitxa mascota (FORM)
    public String fitxaMascota(Mascota mascota, Client client, Model model) {
        log.info("Executant el controlador de mascotes: FORMULARI OBERT...");
        model.addAttribute("pagina", "Clients");

        return "mascotaForm";
    }

    @PostMapping("/medicpet/clients/fitxa/{client_id}/mascotes/desar")                                    // URL 'CREATE' mascota (FORM)***
    public String desarMascota(Mascota mascota, Model model) {
        log.info("Executant el controlador de mascotes: DESANT DADES MASCOTA...");

        mascotaService.save(mascota);

//        client = clientService.getOne(client.getIdclient());
//        model.addAttribute("client", client );
        return "redirect:/medicpet/clients/fitxa/{client_id}";
    }

    @GetMapping("/medicpet/clients/fitxa/{client_id}/mascotes/fitxa/{id_mascota}/editar")                         // URL 'UPDATE' mascota (FORM)
    public String modificarMascota(Mascota mascota, Model model) {
        log.info("Executant el controlador de mascotes: EDITANT...");

        mascota = mascotaService.getOne(mascota.getId_mascota());
        model.addAttribute("mascota", mascota);
        model.addAttribute("pagina", "Clients");
        
        // amb això comentat recupero dades al formulari d'edició...
//        client = clientService.getOne(client.getIdclient());
//        model.addAttribute("client", client );
        
        return "mascotaForm";
    }

    @PostMapping("/medicpet/clients/fitxa/{client_id}/mascotes/eliminar/{id_mascota}")                     // URL 'DELETE' mascota (FORM)
    public String eliminar(Mascota mascota) {
        log.info("Executant el controlador de mascotes: MASCOTA (" + mascota.getNom() + ") ELIMINADA...");

        mascotaService.delete(mascota);
        
        return "redirect:/medicpet/clients/fitxa/{client_id}";
    }

}
