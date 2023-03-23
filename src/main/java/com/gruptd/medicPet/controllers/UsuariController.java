package com.gruptd.medicPet.controllers;

import com.gruptd.medicPet.models.CanviContrasenya;
import com.gruptd.medicPet.models.Usuari;
import com.gruptd.medicPet.services.RolServices;
import com.gruptd.medicPet.services.UsuariServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        CanviContrasenya password = new CanviContrasenya();
        model.addAttribute("userName", username);
        model.addAttribute("treballador", usuari);
        model.addAttribute("rol", rol);
        model.addAttribute("contrasenya", password);
        
        return "perfil";
    }
    
    @PostMapping("/medicpet/perfil/guardar")
    public String modificarUsuari(Usuari usuari) {
        usuariServices.update(usuari);
        
        return "redirect:/medicpet/perfil";
    }
    
    @PostMapping("/medicpet/perfical/canviarContrasenya")
    public String modificarContrasenya(CanviContrasenya password, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuari usuari = usuariServices.getByUsername(username);
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String contrasenyaActualSend = password.getContraActual();
        String contrasenyaActual = usuari.getContrasenya();
        System.out.println(contrasenyaActual);
        System.out.println(contrasenyaActualSend);
        if (passwordEncoder.matches(contrasenyaActualSend, contrasenyaActual)) {
            if (password.getContraNova().equals(password.getContraNovaConf())) {
                String contrasenyaNova = passwordEncoder.encode(password.getContraNova());
                usuari.setContrasenya(contrasenyaNova);
                usuariServices.update(usuari);
            } else {
                return "redirect:/medicpet/perfil?novaInvalida";
            }
        } else {
            return "redirect:/medicpet/perfil?incorrecta";
        }
        
        model.addAttribute("userName", username);
        
        return "redirect:/medicpet/perfil";
    }
}
