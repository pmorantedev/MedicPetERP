package com.gruptd.medicPet;

import com.gruptd.medicPet.dao.ClientDAO;
import com.gruptd.medicPet.dao.MascotaDAO;
import com.gruptd.medicPet.dao.RolDAO;
import com.gruptd.medicPet.dao.UsuariDAO;
import com.gruptd.medicPet.dao.VisitaDAO;
import com.gruptd.medicPet.models.Client;
import com.gruptd.medicPet.models.Mascota;
import com.gruptd.medicPet.models.Rol;
import com.gruptd.medicPet.models.Usuari;
import com.gruptd.medicPet.models.Visita;
import com.gruptd.medicPet.services.UsuariServices;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    
    @Autowired
    private UsuariServices usuariService;
    
    @Autowired
    private RolDAO RolDAO;

    @GetMapping("/login")
    public String inici() {
        log.info("Executant el controlador d'inici");
        return "login";
    }

    @GetMapping("/")
    public String arrel() {
        return "redirect:/medicpet/tractaments";
    }
    
    @GetMapping("/registre")
    public String registre(Usuari usuari) {
        log.info("Executant el controlador de registre");
        return "registre";
    }
    
    @PostMapping("/nou-usuari")
    public String guardar(Usuari usuari){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String testPasswordEncoded = passwordEncoder.encode(usuari.getContrasenya());
        usuari.setContrasenya(testPasswordEncoded);
        usuariService.saveUsuari(usuari);
        return "redirect:/login";
    }

}
