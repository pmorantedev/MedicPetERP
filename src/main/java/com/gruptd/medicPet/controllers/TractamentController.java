package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.services.TractamentServices;
import com.gruptd.medicPet.models.Tractament;
import jakarta.validation.Valid;
import java.util.List;
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

@Controller
@Slf4j
public class TractamentController {

    @Autowired
    private TractamentServices tractamentService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/medicpet/tractaments")
    public String principalTractament(Model model, @Param("paraulaClau") String paraulaClau) {

        Iterable<Tractament> tractaments;
        
        // String paraulaClau = "TRACTAMENT";
        if (paraulaClau != null) {
            String sql = "SELECT * FROM tractament t WHERE CONCAT(t.nom, t.id, t.preu) LIKE '%" + paraulaClau + "%'";
            tractaments = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tractament.class));
        } else {
            tractaments = tractamentService.findAll();
        }
        
        model.addAttribute("tractaments", tractaments);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("userName", username);

        return "tractamentsMain";
    }

    @GetMapping("/medicpet/tractaments/fitxa/{id}")
    public String desarTractament(Tractament tractament, Model model) {
        tractament = tractamentService.getOne(tractament.getId());
        model.addAttribute("tractament", tractament);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userName", username);

        return "tractamentsForm";
    }

    @GetMapping("/medicpet/tractaments/fitxa")
    public String fitxaTractament(Tractament tractament, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userName", username);

        return "tractamentsForm";
    }

    @PostMapping("/medicpet/tractaments/eliminar/{id}")
    public String eliminar(Tractament tractament) {
        tractamentService.delete(tractament);
        return "redirect:/medicpet/tractaments";
    }

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
