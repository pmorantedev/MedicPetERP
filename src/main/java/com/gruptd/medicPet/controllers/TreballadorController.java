package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.Carrec;
import com.gruptd.medicPet.models.Treballador;
import com.gruptd.medicPet.models.Usuari;
import com.gruptd.medicPet.services.CarrecServices;
import com.gruptd.medicPet.services.TreballadorServices;
import com.gruptd.medicPet.services.UsuariServices;
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
 * Controlador per la vista del control dels treballadors
 *
 * @author izan
 */
@Controller
@Slf4j
public class TreballadorController {

    @Autowired
    private UsuariServices usuariService;

    @Autowired
    private TreballadorServices treballadorService;

    @Autowired
    private CarrecServices carrecService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * *
     * Vista general per veure tots els treballadors de la BBDD i poder accedir
     * a la fitxa de cadascun per veure o modificar dades
     *
     * @param model
     * @param paraulaClau
     * @param nomRegistreEliminat
     * @param registreEliminat
     * @return
     */
    @GetMapping("/medicpet/rrhh")
    public String principalTreballadors(Model model,
            @Param("paraulaClau") String paraulaClau,
            @Param("nomRegistreEliminat") String nomRegistreEliminat,
            @RequestParam(name = "registreEliminat", required = false) Boolean registreEliminat) {

        log.info("Executant controlador treballadors: LLISTAT");

        // Mostra alerta per informar a l'usuari que un treballador s'ha eliminat
        if (registreEliminat != null) {
            log.info("[info] Mostrar alerta a l'usuari: TREBALLADOR ELIMINAT");
            model.addAttribute("nomRegistreEliminat", nomRegistreEliminat);
        }

        // Definir/Inicialitzar variables necessàries per la vista
        Iterable<Treballador> treballadors;

        // Recuperar el nom de l'usuari actual
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Recuperar l'objecte Usuari corresponent a l'usuari actual
        Usuari usuari = usuariService.getByUsername(username);
        // Accedir a l'atribut 'Nom' per mostrar-lo al header
        String nomUsuariComplert = usuari.getNom();
        model.addAttribute("userName", username);
        model.addAttribute("nomUsuariComplert", nomUsuariComplert);

        // Codi pel cercador
        if (paraulaClau != null) {
            String sql = "SELECT * FROM treballador t WHERE CONCAT(t.nom_complet, t.telefon, t.email, t.adreca, t.carrec_aux, t.carrec_id) LIKE '%" + paraulaClau + "%'";
            treballadors = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Treballador.class));
        } else {
            treballadors = treballadorService.findAll();
        }

        // Passar variables a la vista
        model.addAttribute("treballadors", treballadors);
        model.addAttribute("pagina", "RRHH");

        return "rrhhMain";
    }

    /**
     * *
     * Controlador per modificar les dades d'un treballador seleccionat.
     *
     * @param treballador
     * @param model
     * @return
     */
    @GetMapping("/medicpet/rrhh/fitxa/{id}")
    public String modificarTreballador(Treballador treballador, Model model) {
        treballador = treballadorService.getOne(treballador.getId());
        model.addAttribute("treballador", treballador);

        // Recuperar el nom de l'usuari actual
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Recuperar l'objecte Usuari corresponent a l'usuari actual
        Usuari usuari = usuariService.getByUsername(username);
        // Accedir a l'atribut 'Nom' per mostrar-lo al header
        String nomUsuariComplert = usuari.getNom();
        model.addAttribute("userName", username);
        model.addAttribute("nomUsuariComplert", nomUsuariComplert);

        model.addAttribute("pagina", "RRHH");
        Iterable<Carrec> carrecs = carrecService.findAll();
        model.addAttribute("carrecs", carrecs);

        return "rrhhForm";
    }

    /**
     * *
     * Controlador per afegir un nou treballador a la BBDD.
     *
     * @param treballador
     * @param model
     * @return
     */
    @GetMapping("/medicpet/rrhh/fitxa")
    public String fitxaTractament(Treballador treballador, Model model) {

        // Recuperar el nom de l'usuari actual
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Recuperar l'objecte Usuari corresponent a l'usuari actual
        Usuari usuari = usuariService.getByUsername(username);
        // Accedir a l'atribut 'Nom' per mostrar-lo al header
        String nomUsuariComplert = usuari.getNom();
        model.addAttribute("userName", username);
        model.addAttribute("nomUsuariComplert", nomUsuariComplert);

        model.addAttribute("pagina", "RRHH");
        Iterable<Carrec> carrecs = carrecService.findAll();
        model.addAttribute("carrecs", carrecs);

        return "rrhhForm";
    }

    /**
     * *
     * Controlador per eliminar un treballador de la BBDD.
     *
     * @param treballador
     * @param redirectAtr
     * @return
     */
    @PostMapping("/medicpet/rrhh/eliminar/{id}")
    public String eliminar(Treballador treballador, RedirectAttributes redirectAtr) {

        // Recupero treballador per mostrar el nom per consola i passar-lo a la vista
        treballador = treballadorService.getOne(treballador.getId());
        log.info("Executant controlador clients: TREBALLADOR ELIMINAT ( ID:" + treballador.getId() + ", " + treballador.getNomComplet() + " )...");
        redirectAtr.addAttribute("nomRegistreEliminat", treballador.getNomComplet());

        // Executo l'acció d'eliminar
        treballadorService.delete(treballador);
        redirectAtr.addAttribute("registreEliminat", true);

        return "redirect:/medicpet/rrhh";
    }

    /**
     * *
     * Emmagatzema un nou treballador si no hi ha cap error.
     *
     * @param treballador
     * @param errors
     * @return
     */
    @PostMapping("/medicpet/rrhh/guardar")
    public String guardar(Treballador treballador, Errors errors) {
        if (errors.hasErrors()) {
            log.info("------------------------- Entra a hasErrors dentro de treballadors");
            return "rrhhForm";
        }
        treballadorService.save(treballador);
        return "redirect:/medicpet/rrhh";
    }
}
