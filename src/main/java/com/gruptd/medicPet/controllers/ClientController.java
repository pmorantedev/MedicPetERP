package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.Client;
import com.gruptd.medicPet.models.Mascota;
import com.gruptd.medicPet.services.ClientServices;
import com.gruptd.medicPet.services.MascotaServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        log.info("Executant el controlador de clients: LLISTAT");
        Iterable<Client> clients = clientService.findAll();
        Iterable<Mascota> mascotes = mascotaService.findAll();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        model.addAttribute("clients", clients);
        model.addAttribute("mascotes", mascotes);
        model.addAttribute("userName", username);
        
        return "clientsMain";
    }
    
    @GetMapping("/medicpet/clients/fitxa")                                      // URL fitxa client (FORM)
    public String fitxaClient(Client client, Model model) {
        log.info("Executant el controlador de clients: OBRIR FITXA NOVA...");
        
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userName", username);
        
        return "clientForm";
    }
    
    @PostMapping("/medicpet/clients/desar")                                     // URL 'CREATE' client (FORM)
    public String desarClient(Client client){
        log.info("Executant el controlador de clients: DESANT DADES CLIENT...");
        
        clientService.save(client);
        return "redirect:/medicpet/clients";
    }
    
    @GetMapping("/medicpet/clients/fitxa/{idclient}")                           // URL 'UPDATE' client (FORM) ****
    public String modificarClient(Client client, Model model) {
        log.info("Executant el controlador de clients: OBRIR FITXA EXISTENT...");
        
        client = clientService.getOne(client.getIdclient());
        model.addAttribute("client", client );
        
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userName", username);
        
        return "clientForm";
    }
    
    @PostMapping("/medicpet/clients/eliminar/{idclient}")                       // URL 'DELETE' client (FORM)
    public String eliminar(Client client, Model model){        
        
        // TO-DO: detectar si hi havia mascotes associades i llistar-les al log
//        if (!client.getMascotes().isEmpty()) {
//            for(int i = 0; i<client.getMascotes().size(); i++)
//                log.info("Executant el controlador de clients: CLIENT ("+client.getIdclient()+") / MASCOTA ASSOCIADA ("+client.getMascotes().get(i).getId_mascota()+") ELIMINADA");
//        }
        
        clientService.delete(client);
        log.info("Executant el controlador de clients: CLIENT ("+client.getIdclient()+") ELIMINAT");
        
        return "redirect:/medicpet/clients";
    }
    
}