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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String principalTreballadors(Model model, @Param("paraulaClau") String paraulaClau) {
        log.info("Executant el controlador de treballador");

        Iterable<Treballador> treballadors;

        // String paraulaClau = "TRACTAMENT";
        if (paraulaClau != null) {
            String sql = "SELECT * FROM treballador t WHERE CONCAT(t.nom_complet, t.telefon, t.email, t.adreca, t.carrec_aux, t.carrec_id) LIKE '%" + paraulaClau + "%'";
            treballadors = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Treballador.class));
        } else {
            treballadors = treballadorService.findAll();
        }

        model.addAttribute("treballadors", treballadors);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
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
    public String eliminar(Treballador treballador) {
        treballadorService.delete(treballador);
        return "redirect:/medicpet/rrhh";
    }

    @PostMapping("/medicpet/rrhh/guardar")
    public String guardar(Treballador treballador, Errors errors) {
        if (errors.hasErrors()) {
            return "rrhhForm"; 
        }
        treballadorService.save(treballador);
        return "redirect:/medicpet/rrhh";
    }
}
