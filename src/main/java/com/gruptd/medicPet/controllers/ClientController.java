package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.Client;
import com.gruptd.medicPet.services.ClientServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ClientController {

    @Autowired
    private ClientServices clientService;
    
    @GetMapping("/medicpet/clients")
    public String principalClients(Model model) {
        log.info("Executant el controlador de clients");
        Iterable<Client> clients = clientService.findAll();
        
        model.addAttribute("clients", clients);
        
        return "clientsMain";
    }
    
    @GetMapping("/medicpet/clients/fitxa")                                      // URL fitxa client (FORM)
    public String fitxaClient(Client client) {
        
        return "clientForm";
    }
    
    @PostMapping("/medicpet/clients/desar")                                     // URL 'CREATE' client (FORM)
    public String desarClient(Client client){
        clientService.save(client);
        return "redirect:/medicpet/clients";
    }
    
    @PostMapping("/medicpet/clients/fitxa/{idclient}")                                // URL 'WRITE' client (FORM)
    public String modificarClient(Client client, Model model) {
        client = clientService.getOne(client.getIdclient());
        model.addAttribute("client", client );
        return "clientForm";
    }
    
    @PostMapping("/medicpet/clients/eliminar/{idclient}")                             // URL 'DELETE' client (FORM)
    public String eliminar(Client client){
        clientService.delete(client);
        return "redirect:/medicpet/clients";
    }
    
}
