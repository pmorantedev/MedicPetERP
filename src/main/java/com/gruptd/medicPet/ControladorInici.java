package com.gruptd.medicPet;

import com.gruptd.medicPet.dao.ClientDAO;
import com.gruptd.medicPet.dao.MascotaDAO;
import com.gruptd.medicPet.models.Client;
import com.gruptd.medicPet.models.Mascota;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ClientDAO clientDao;

    @Autowired
    private MascotaDAO mascotaDao;

    @GetMapping("/")
    public String inici() {
        log.info("Executant el controlador d'inici");

        Iterable<Client> personas = clientDao.findAll();
        log.info(">>> Clientes de la BBDD:");
        personas.forEach((t) -> {
            log.info(t.getNomComplert());
        });

        Iterable<Mascota> mascotas = mascotaDao.findAll();
        log.info(">>> Mascotes de la  de la BBDD:");
        mascotas.forEach((m) -> {
            log.info(m.getNom());
        });

//        Iterable<Mascota> mascotas = mascotaDao.findAll();
//        log.info(">>> Mascotes de la  de la BBDD:");
//        mascotas.forEach((m) -> {
//            log.info(m.getNom());
//        });
        return "login";
    }

    @GetMapping("/registre")
    public String registre() {
        log.info("Executant el controlador de registre");
        return "registre";
    }
}
