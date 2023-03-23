package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.Carrec;
import com.gruptd.medicPet.models.Treballador;
import com.gruptd.medicPet.services.CarrecServices;
import com.gruptd.medicPet.services.TreballadorServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class TreballadorController {
    @Autowired
    private TreballadorServices treballadorService;
    
    @Autowired
    private CarrecServices carrecService;
    
    @GetMapping("/medicpet/rrhh")
    public String principalTreballadors(Model model) {
        log.info("Executant el controlador de treballador");
        Iterable<Treballador> treballadors = treballadorService.findAll();
        model.addAttribute("treballadors", treballadors);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userName", username);
        
        return "rrhhMain";
    }
    
    @GetMapping("/medicpet/rrhh/fitxa/{id}")
    public String modificarTreballador(Treballador treballador, Model model) {
        treballador = treballadorService.getOne(treballador.getId());
        model.addAttribute("treballador", treballador);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userName", username);
        
        return "rrhhForm";
    }
    
    @GetMapping("/medicpet/rrhh/fitxa")
    public String fitxaTractament(Treballador treballador, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userName", username);
        
        return "rrhhForm";
    }
    
    @PostMapping("/medicpet/rrhh/eliminar/{id}")
    public String eliminar(Treballador treballador){
        treballadorService.delete(treballador);
        return "redirect:/medicpet/rrhh";
    }
    
    @PostMapping("/medicpet/rrhh/guardar")
    public String guardar(Treballador treballador){
        treballadorService.save(treballador);
        return "redirect:/medicpet/rrhh";
    }
}
