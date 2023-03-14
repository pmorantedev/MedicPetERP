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

    @Autowired
    private VisitaDAO visitaDao;

    @Autowired
    private RolDAO rolDao;

    @Autowired
    private UsuariDAO usuariDao;

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

        Iterable<Visita> visitas = visitaDao.findAll();
        log.info(">>> Visites de la  de la BBDD:");
        visitas.forEach((v) -> {
            log.info(v.getDiagnostic());
        });

        Iterable<Rol> rols = rolDao.findAll();
        log.info(">>> Rols de la  de la BBDD:");
        rols.forEach((v) -> {
            log.info(v.getNom());
        });

        //el PrimaryKey no es ID Auto
        Iterable<Usuari> usuaris = usuariDao.findAll();
        log.info(">>> Usuaris de la  de la BBDD:");
        usuaris.forEach((v) -> {
            log.info(v.getNom());
        });
        return "login";
    }

    @GetMapping("/registre")
    public String registre() {
        log.info("Executant el controlador de registre");
        return "registre";
    }

}
