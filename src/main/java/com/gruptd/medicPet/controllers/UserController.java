package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.Usuari;
import com.gruptd.medicPet.services.UsuariServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UsuariServices ususariServices;

    @GetMapping("/rrhh")
    public String rrhhMain() {
        log.info("Executant el controlador d'usuaris RRHH");
        Iterable<Usuari> factures = ususariServices.findAllUsuaris();
        log.info(">>> Usuaris de la BBDD:");
        factures.forEach((t) -> {
            log.info(t.getNom());
        });

        return "rrhhMain";
    }
}
