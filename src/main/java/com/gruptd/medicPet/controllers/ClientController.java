package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.Client;
import com.gruptd.medicPet.models.Mascota;
import com.gruptd.medicPet.services.ClientServices;
import com.gruptd.medicPet.services.MascotaServices;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ClientController {

    @Autowired
    private ClientServices clientService;
    
    @Autowired
    private MascotaServices mascotaService;
    
    @GetMapping("/medicpet/clients")
    public String principalClients(Model model) {                               // URL 'READ' clients (LIST)
        log.info("Executant controlador clients: LLISTAT");
        Iterable<Client> clients = clientService.findAll();
        Iterable<Mascota> mascotes = mascotaService.findAll();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        model.addAttribute("clients", clients);
        model.addAttribute("mascotes", mascotes);
        model.addAttribute("userName", username);
        model.addAttribute("pagina", "Clients");
        
        return "clientsMain";
    }
    
    @GetMapping("/medicpet/clients/fitxa")                                      // URL mostrar fitxa client (FORM)
    public String crearClient(Client client, Model model) {
        
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userName", username);
        model.addAttribute("pagina", "Clients");
        
        log.info("Executant controlador clients: FITXA NOU CLIENT...");
        
        return "clientForm";
    }
    
    @PostMapping("/medicpet/clients/desar")                                     // URL 'CREATE' / 'UPDATE' client (FORM)
    public String desarClient(@Valid Client client, Errors errors){
        
        log.info("Executant controlador clients: VALIDANT DADES CLIENT...");        
        if(errors.hasErrors()){ // Si s'han produït errors...
             return "clientForm"; // Mantenim l'usuari a la pàgina del formulari
        }
        
        if ( client.getIdclient() == null ) {
            clientService.save(client);
            log.info("Executant controlador clients: NOU CLIENT DESAT ( ID:"+client.getIdclient()+", "+client.getNomComplert()+" )...");
        } else {
            clientService.update(client);
            log.info("Executant controlador clients: CLIENT ACTUALITZAT ( ID:"+client.getIdclient()+", "+client.getNomComplert()+" )...");
        }
        
        return "redirect:/medicpet/clients";
    }
    
    @GetMapping("/medicpet/clients/fitxa/{idclient}")                           // URL 'EDIT' client (FORM) ****
    public String modificarClient(Client client, Model model) {
        
        client = clientService.getOne(client.getIdclient());
        model.addAttribute("client", client );
        log.info("Executant controlador clients: MOSTRAR FITXA CLIENT EXISTENT ( ID:"+client.getIdclient()+", "+client.getNomComplert()+" )...");
        
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userName", username);
        model.addAttribute("pagina", "Clients");
                
        return "clientForm";
    }
    
    @PostMapping("/medicpet/clients/eliminar/{idclient}")                       // URL 'DELETE' client (FORM)
    public String eliminar(@PathVariable Long idclient, Client client, Model model){        
        
        // TO-DO: detectar si hi havia mascotes associades i llistar-les al log
//        if (!client.getMascotes().isEmpty()) {
//            for(int i = 0; i<client.getMascotes().size(); i++)
//                log.info("Executant el controlador de clients: CLIENT ("+client.getIdclient()+") / MASCOTA ASSOCIADA ("+client.getMascotes().get(i).getId_mascota()+") ELIMINADA");
//        }
        client = clientService.getOne(idclient);
        clientService.delete(client);
        log.info("Executant el controlador de clients: CLIENT ELIMINAT ( ID:"+client.getIdclient()+", "+client.getNomComplert()+" )...");
        
        return "redirect:/medicpet/clients";
    }
    
}
