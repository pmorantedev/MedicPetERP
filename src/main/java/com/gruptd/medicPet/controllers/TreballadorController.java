package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.Carrec;
import com.gruptd.medicPet.models.Treballador;
import com.gruptd.medicPet.services.CarrecServices;
import com.gruptd.medicPet.services.TreballadorServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class TreballadorController {

    @Autowired
    private TreballadorServices treballadorService;

    @Autowired
    private CarrecServices carrecService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
         String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Codi pel cercador
        if (paraulaClau != null) {
            String sql = "SELECT * FROM treballador t WHERE CONCAT(t.nom_complet, t.telefon, t.email, t.adreca, t.carrec_aux, t.carrec_id) LIKE '%" + paraulaClau + "%'";
            treballadors = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Treballador.class));
        } else {
            treballadors = treballadorService.findAll();
        }

        // Passar variables a la vista
        model.addAttribute("treballadors", treballadors);       
        model.addAttribute("userName", username);
        model.addAttribute("pagina", "RRHH");

        return "rrhhMain";
    }

    @GetMapping("/medicpet/rrhh/fitxa/{id}")
    public String modificarTreballador(Treballador treballador, Model model) {
        treballador = treballadorService.getOne(treballador.getId());
        model.addAttribute("treballador", treballador);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userName", username);
        model.addAttribute("pagina", "RRHH");
        Iterable<Carrec> carrecs = carrecService.findAll();
        model.addAttribute("carrecs", carrecs);

        return "rrhhForm";
    }

    @GetMapping("/medicpet/rrhh/fitxa")
    public String fitxaTractament(Treballador treballador, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userName", username);
        model.addAttribute("pagina", "RRHH");
        Iterable<Carrec> carrecs = carrecService.findAll();
        model.addAttribute("carrecs", carrecs);

        return "rrhhForm";
    }

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

    @PostMapping("/medicpet/rrhh/guardar")
    public String guardar(Treballador treballador) {
        treballadorService.save(treballador);
        return "redirect:/medicpet/rrhh";
    }
}
