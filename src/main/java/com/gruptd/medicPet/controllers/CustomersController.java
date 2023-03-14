package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.Client;
import com.gruptd.medicPet.services.ClientServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class CustomersController {

    @Autowired
    private ClientServices clientService;
    
    @GetMapping("/clients")
    public String clientsMain() {
        log.info("Executant el controlador de clients");
        Iterable<Client> factures = clientService.findAllClients();
        log.info(">>> Factures de la BBDD:");
        factures.forEach((t) -> {
            log.info(t.getEmail());
        });

        return "clientsMain";
    }
}
