package com.gruptd.medicPet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author mllanas
 */
//sobre @Slf4j: https://stackabuse.com/guide-to-logging-in-spring-boot/
/*Anotació @RestController fa refèrencia a la classe RestController que accepta les
peticions HTTP
*que es fan des del navegador.
 */
@Controller
@Slf4j  // Anotació que permet utilitzar l'API de Login
public class ControladorInici {

    @GetMapping("/")
    public String inici() {
        log.info("Executant el controlador d'inici");
        return "login";
    }

    @GetMapping("/registre")
    public String registre() {
        log.info("Executant el controlador de registre");
        return "registre";
    }
}
