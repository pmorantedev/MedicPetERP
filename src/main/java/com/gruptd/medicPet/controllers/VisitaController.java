package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.Carrec;
import com.gruptd.medicPet.models.Mascota;
import com.gruptd.medicPet.models.Tractament;
import com.gruptd.medicPet.models.Treballador;
import com.gruptd.medicPet.models.Usuari;
import com.gruptd.medicPet.models.Visita;
import com.gruptd.medicPet.services.CarrecServices;
import com.gruptd.medicPet.services.MascotaServices;
import com.gruptd.medicPet.services.TractamentServices;
import com.gruptd.medicPet.services.TreballadorServices;
import com.gruptd.medicPet.services.UsuariServices;
import com.gruptd.medicPet.services.VisitaServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * *
 * Controlador per la vista del control de vistas
 *
 * @author izan
 */
@Controller
@Slf4j
public class VisitaController {

    @Autowired
    private TractamentServices tractamentServices;
    @Autowired
    private VisitaServices visitaServices;
    @Autowired
    private TreballadorServices treballadorServices;
    @Autowired
    private CarrecServices carrecServices;
    @Autowired
    private MascotaServices mascotaServices;
    @Autowired
    private UsuariServices usuariService;

    /**
     * *
     * Controlador per les visites d'una mascota. Permet veure totes les visites
     * de la mascota sleccionada
     *
     * @param mascota
     * @param visita
     * @param tractament
     * @param model
     * @return
     */
    @GetMapping("/medicpet/visites/{id_mascota}/fitxa")
    public String principalVisita(Mascota mascota, Visita visita, Tractament tractament, Model model) {

        log.info("Executant controlador visites: FORMULARI");

        Carrec carrec = carrecServices.getOne(1L);
        Iterable<Treballador> veterinaris = treballadorServices.getByCarrec(carrec);
        Mascota mascota2 = mascotaServices.getOne(mascota.getId_mascota());

        log.info("tractamentServices...");
        Iterable<Tractament> tractaments = tractamentServices.findAll();
        log.info(tractaments.toString());

        model.addAttribute("mascota", mascota2);
        model.addAttribute("pagina", "Clients");
        model.addAttribute("veterinaris", veterinaris);
        model.addAttribute("tractaments", tractaments);

        // Recuperar el nom de l'usuari actual
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Recuperar l'objecte Usuari corresponent a l'usuari actual
        Usuari usuari = usuariService.getByUsername(username);
        // Accedir als atributs
        String nomUsuariComplert = usuari.getNom();
        model.addAttribute("userName", username);
        model.addAttribute("nomUsuariComplert", nomUsuariComplert);

        return "visitaForm";
    }

    /**
     * *
     * Desa la visita a la BBDD. Té en compte l'id de la mascota
     *
     * @param visita
     * @param model
     * @param errors
     * @return
     */
    @PostMapping("/medicpet/visites/fitxa/desar")
    public String desarVisita(Visita visita, Model model, Errors errors) {

        if (errors.hasErrors()) { // Si s'han produït errors...
            return "visitaForm"; // Mantenim l'usuari a la pàgina del formulari
        }
        if (visita.getId() == null) {
            visitaServices.save(visita);
        } else {
            visitaServices.update(visita);
        }

        return "redirect:/medicpet/clients";
    }

    /**
     * *
     * Controlador per editar una visita d'una mascota. La mascota no es pot
     * modificar, ja que la visita es selecciona desde una mascota
     *
     * @param mascota
     * @param visita
     * @param tractament
     * @param model
     * @return
     */
    @GetMapping("/medicpet/visites/{id_mascota}/fitxa/{id}/editar")
    public String modificarVisita(Mascota mascota, Visita visita, Tractament tractament, Model model) {

        log.info("Executant controlador visites: EDITANT FORMULARI");

        visita = visitaServices.getOne(visita.getId());
        model.addAttribute("visita", visita);
        Carrec carrec = carrecServices.getOne(1L);
        Iterable<Treballador> veterinaris = treballadorServices.getByCarrec(carrec);
        Mascota mascota2 = mascotaServices.getOne(mascota.getId_mascota());

        log.info("tractamentServices...");
        Iterable<Tractament> tractaments = tractamentServices.findAll();
        log.info(tractaments.toString());

        model.addAttribute("mascota", mascota2);
        model.addAttribute("pagina", "Clients");
        model.addAttribute("veterinaris", veterinaris);
        model.addAttribute("tractaments", tractaments);

        // Recuperar el nom de l'usuari actual
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Recuperar l'objecte Usuari corresponent a l'usuari actual
        Usuari usuari = usuariService.getByUsername(username);
        // Accedir als atributs
        String nomUsuariComplert = usuari.getNom();
        model.addAttribute("userName", username);
        model.addAttribute("nomUsuariComplert", nomUsuariComplert);

        return "visitaForm";
    }

    /**
     * *
     * Controlador per eliminar un avisita d'una mascota previament
     * seleccionada.
     *
     * @param visita_id
     * @param redirectAtr
     * @return
     */
    @PostMapping("/medicpet/clients/fitxa/{client_id}/mascotes/fitxa/{id_mascota}/visites/eliminar/{visita_id}")
    public String eliminar(@PathVariable Long visita_id, RedirectAttributes redirectAtr) {

        // Recupero visita per mostrar la data per consola i passar-la a la vista
        Visita visita = visitaServices.getOne(visita_id);

        // Fa que l'atribut 'nomRegistreEliminat' estigui disponible a la vista redirigida i mostrar l'alerta.
        redirectAtr.addFlashAttribute("nomRegistreEliminat", visita.getDiagnostic());
        redirectAtr.addFlashAttribute("dataRegistreEliminat", visita.getData_visita());

        // Eliminem visita
        log.info("Executant controlador visites: VISITA ELIMINADA( ID:" + visita.getId() + ", " + visita.getDiagnostic() + ", " + visita.getData_visita() + ")...");
        visitaServices.delete(visita);
        redirectAtr.addAttribute("registreEliminat", true);

        return "redirect:/medicpet/clients/fitxa/{client_id}/mascotes/fitxa/{id_mascota}/editar";
    }
}
