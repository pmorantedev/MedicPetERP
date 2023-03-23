package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.Rol;
import com.gruptd.medicPet.models.Usuari;
import com.gruptd.medicPet.services.RolServices;
import com.gruptd.medicPet.services.UsuariServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UsuariController {

    @Autowired
    private UsuariServices usuariServices;
    
    @Autowired
    private RolServices rolServices;

    @GetMapping("/medicpet/perfil")
    public String perfilUsuari(Model model) {
        
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuari usuari = usuariServices.getByUsername(username);
        String rol = usuari.getRol_id().getNom();
        model.addAttribute("userName", username);
        model.addAttribute("treballador", usuari);
        model.addAttribute("rol", rol);
        
        return "perfil";
    }
    
    @PostMapping("/medicpet/perfil/guardar")
    public String modificarUsuari(Usuari usuari) {
        usuariServices.update(usuari);
        
        return "redirect:/medicpet/perfil";
    }
}
