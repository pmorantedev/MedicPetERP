package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.Usuari;
import com.gruptd.medicPet.services.UsuariServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class UsuariController {

    @Autowired
    private UsuariServices ususariServices;

    @GetMapping("/medicpet/perfil")
    public String rrhhMain() {
        log.info("Executant el controlador d'usuaris RRHH");
        Iterable<Usuari> factures = ususariServices.findAll();
        log.info(">>> Usuaris de la BBDD:");
        factures.forEach((t) -> {
            log.info(t.getNom());
        });

        return "rrhhMain";
    }
}
