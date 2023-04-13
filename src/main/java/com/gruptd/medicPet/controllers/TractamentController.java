package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.services.TractamentServices;
import com.gruptd.medicPet.models.Tractament;
import com.gruptd.medicPet.models.Usuari;
import com.gruptd.medicPet.services.UsuariServices;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * *
 * Controlador del modul de tractaments
 *
 * @author izan
 */
@Controller
@Slf4j
public class TractamentController {

    @Autowired
    private UsuariServices usuariService;

    @Autowired
    private TractamentServices tractamentService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * *
     * Vista general del modul de tractaments. Permet veure els tractaments de
     * la BBDD i els seus atributs. També permet accedir per als tractaments per
     * modificar-los
     *
     * @param model Model
     * @param paraulaClau Cercador
     * @param nomRegistreEliminat nomRegistreEliminat
     * @param registreEliminat registreEliminat
     * @return
     */
    @GetMapping("/medicpet/tractaments")
    public String principalTractament(Model model,
            @Param("paraulaClau") String paraulaClau,
            @Param("nomRegistreEliminat") String nomRegistreEliminat,
            @RequestParam(name = "registreEliminat", required = false) Boolean registreEliminat) {

        log.info("Executant controlador tractaments: LLISTAT");

        // Mostra alerta per informar a l'usuari que un tractament s'ha eliminat
        if (registreEliminat != null) {
            log.info("[info] Mostrar alerta a l'usuari: TRACTAMENT ELIMINAT");
            model.addAttribute("nomRegistreEliminat", nomRegistreEliminat);
        }

        // Definir/Inicialitzar variables necessàries per la vista
        Iterable<Tractament> tractaments;

        // Recuperar el nom de l'usuari actual
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Recuperar l'objecte Usuari corresponent a l'usuari actual
        Usuari usuari = usuariService.getByUsername(username);
        // Accedir als atributs
        String nomUsuariComplert = usuari.getNom();
        String rolUsuari = usuari.getRol_id().getNom();

        // Codi pel cercador
        if (paraulaClau != null) {
            String sql = "SELECT * FROM tractament t WHERE CONCAT(t.id, t.nom, t.preu) LIKE '%" + paraulaClau + "%'";
            tractaments = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tractament.class));
        } else {
            tractaments = tractamentService.findAll();
        }

        // Passar variables a la vista
        model.addAttribute("tractaments", tractaments);
        model.addAttribute("userName", username);
        model.addAttribute("nomUsuariComplert", nomUsuariComplert);
        model.addAttribute("rolUsuari", rolUsuari);
        model.addAttribute("pagina", "Tractaments");

        return "tractamentsMain";
    }

    /**
     * *
     * Controlador per gestionar les dades d'un tractament. Permet modificar els
     * atributs d'un tractament i guardar-lo a la BBDD
     *
     * @param tractament
     * @param model
     * @return
     */
    @GetMapping("/medicpet/tractaments/fitxa/{id}")
    public String desarTractament(Tractament tractament, Model model) {
        tractament = tractamentService.getOne(tractament.getId());
        model.addAttribute("tractament", tractament);

        // Recuperar el nom de l'usuari actual
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Recuperar l'objecte Usuari corresponent a l'usuari actual
        Usuari usuari = usuariService.getByUsername(username);
        // Accedir als atributs
        String nomUsuariComplert = usuari.getNom();
        String rolUsuari = usuari.getRol_id().getNom();
        model.addAttribute("userName", username);
        model.addAttribute("nomUsuariComplert", nomUsuariComplert);
        model.addAttribute("rolUsuari", rolUsuari);

        model.addAttribute("pagina", "Tractaments");

        return "tractamentsForm";
    }

    /**
     * *
     * Controlador per crear un nou tractament.
     *
     * @param tractament
     * @param model
     * @return
     */
    @GetMapping("/medicpet/tractaments/fitxa")
    public String fitxaTractament(Tractament tractament, Model model) {

        // Recuperar el nom de l'usuari actual
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Recuperar l'objecte Usuari corresponent a l'usuari actual
        Usuari usuari = usuariService.getByUsername(username);
        // Accedir als atributs
        String nomUsuariComplert = usuari.getNom();
        String rolUsuari = usuari.getRol_id().getNom();
        model.addAttribute("userName", username);
        model.addAttribute("nomUsuariComplert", nomUsuariComplert);
        model.addAttribute("rolUsuari", rolUsuari);

        model.addAttribute("pagina", "Tractaments");

        return "tractamentsForm";
    }

    /**
     * *
     * Controlador que elimina un tractament seleccionat.
     *
     * @param tractament
     * @param redirectAtr
     * @return
     */
    @PostMapping("/medicpet/tractaments/eliminar/{id}")
    public String eliminar(Tractament tractament, RedirectAttributes redirectAtr) {

        // Recupero tractament per mostrar el nom per consola i passar-lo a la vista
        tractament = tractamentService.getOne(tractament.getId());
        log.info("Executant controlador tractaments: TRACTAMENT ELIMINAT ( ID:" + tractament.getId() + ", " + tractament.getNom() + " )...");
        redirectAtr.addAttribute("nomRegistreEliminat", tractament.getNom());

        // Executo l'acció d'eliminar
        tractamentService.delete(tractament);
        redirectAtr.addAttribute("registreEliminat", true);

        return "redirect:/medicpet/tractaments";
    }

    /**
     * *
     * Controlador per guardar els atributs nous o modificats d'un tractament.
     *
     * @param tractament
     * @param errors
     * @return
     */
    @PostMapping("/medicpet/tractaments/guardar")
    public String guardar(@Valid Tractament tractament, Errors errors) {

        if (errors.hasErrors()) { //Si s'han produït errors...
            log.info("Entro en errores");
            return "tractamentsForm";
        }
        tractamentService.save(tractament);
        return "redirect:/medicpet/tractaments";
    }

}
